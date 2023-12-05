package com.beezon.couriers_tracking.service

import com.beezon.couriers_tracking.client.DeliveryClient
import com.beezon.couriers_tracking.client.response.delivery.DeliveryInfoResponse
import com.beezon.couriers_tracking.controller.error.ResourceNotFoundException
import com.beezon.couriers_tracking.controller.request.CreateDeliveryRequest
import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus
import com.beezon.couriers_tracking.enums.VisitStatus
import com.beezon.couriers_tracking.enums.VisitStatus.PENDING
import com.beezon.couriers_tracking.enums.VisitStatus.VISITED
import com.beezon.couriers_tracking.repository.DeliveryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DeliveryServiceImpl(
    private val deliveryClient: DeliveryClient,
    private val deliveryRepository: DeliveryRepository
): DeliveryService {
    val log: Logger = LoggerFactory.getLogger(DeliveryServiceImpl::class.java)
    val workingStatuses = listOf(DeliveryStatus.IN_PROCESS, DeliveryStatus.WAITING)

    override fun createDelivery(request: CreateDeliveryRequest): Delivery {
        val regex = Regex("/route/([\\w-]+)")
        val matchResult = regex.find(request.url)
        val id = matchResult?.groups?.get(1)?.value
            ?: throw IllegalArgumentException("Cannot find delivery id in ${request.url}")

        val deliveryInfo = deliveryClient.getDeliveryInfo(id)
        val delivery = map(deliveryInfo, id, request.orderNum)
        return deliveryRepository.save(delivery)
    }

    override fun getDelivery(id: String): Delivery {
        return deliveryRepository.findById(id)
            .orElseThrow { ResourceNotFoundException() }
    }

    override fun getAllDeliveries(statuses: List<DeliveryStatus>?): List<Delivery> {
        statuses?.let {
            return deliveryRepository.findAllByStatusIn(statuses)
        }

        return deliveryRepository.findAll().toList()
    }

    override fun updateDeliveryState() {
        val deliveries = deliveryRepository.findAllByStatusIn(workingStatuses)
        log.info("Try to update {} deliveries", deliveries.size)

        deliveries.map {
            val deliveryInfo = deliveryClient.getDeliveryInfo(it.id)
            val delivery = map(deliveryInfo, it.id, it.orderNum)
            deliveryRepository.save(delivery)
            log.info("Delivery with id: {} updated", delivery.id)
        }
    }

    private fun map(response: DeliveryInfoResponse, id: String, orderNum: String?): Delivery {
        val src = response.routePoints[0]
        val dst = response.routePoints[1]

        return Delivery(
            id,
            description = response.summary,
            performer = getPerformerDsc(response.description, response.performer.vehicleNumber),
            status = getStatus(src.status, dst.status),
            addressFrom = src.address,
            phoneFrom = src.contact?.phone,
            whoRequested = getWhoRequested(src.comment),
            addressTo = dst.address,
            phoneTo = dst.contact?.phone,
            commentTo = dst.comment,
            createdDate = Instant.now(),
            updatedDate = Instant.now(),
            orderNum = orderNum
        )
    }

    private fun getPerformerDsc(dsc: String?, vehicleNumber: String?):String {
        return "${dsc ?: "Мотоцикл"} ${vehicleNumber ?: ""}"
    }

    private fun getWhoRequested(comment: String?): String {
        val regex = Regex("^(.*?)Glass door between 2nd and 3rd entrances")
        return comment?.let {
            val matchResult = regex.find(it)
            matchResult?.groups?.get(1)?.value?.trim()
        } ?: comment
        ?: ""
    }

    private fun getStatus(srcStatus: VisitStatus, dstStatus: VisitStatus): DeliveryStatus {
        if (srcStatus == PENDING && dstStatus == PENDING) {
            return DeliveryStatus.WAITING
        } else if (srcStatus == VISITED && dstStatus == PENDING) {
            return DeliveryStatus.IN_PROCESS
        } else if (srcStatus == VISITED && dstStatus == VISITED) {
            return DeliveryStatus.COMPLETED
        }

        throw UnsupportedOperationException("Got unexpected state for statuses src: $srcStatus and dst $dstStatus")
    }
}