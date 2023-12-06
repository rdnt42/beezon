package com.beezon.couriers_tracking.service

import com.beezon.couriers_tracking.client.DeliveryClient
import com.beezon.couriers_tracking.controller.error.ResourceNotFoundException
import com.beezon.couriers_tracking.controller.request.CreateDeliveryRequest
import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus
import com.beezon.couriers_tracking.repository.DeliveryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DeliveryServiceImpl(
    private val deliveryClient: DeliveryClient,
    private val deliveryRepository: DeliveryRepository,
    private val deliveryMapper: DeliveryMapper
): DeliveryService {
    val log: Logger = LoggerFactory.getLogger(DeliveryServiceImpl::class.java)
    val workingStatuses = listOf(DeliveryStatus.IN_PROCESS, DeliveryStatus.WAITING)

    override fun createDelivery(request: CreateDeliveryRequest): Delivery {
        val regex = Regex("/route/([\\w-]+)")
        val matchResult = regex.find(request.url)
        val id = matchResult?.groups?.get(1)?.value
            ?: throw IllegalArgumentException("Cannot find delivery id in ${request.url}")

        val deliveryInfo = deliveryClient.getDeliveryInfo(id)
        val delivery = deliveryMapper.map(deliveryInfo, id, request.orderNum)
        return deliveryRepository.save(delivery)
    }

    override fun getDelivery(id: String): Delivery {
        return deliveryRepository.findById(id)
            .orElseThrow { ResourceNotFoundException() }
    }

    override fun getAllDeliveries(statuses: List<DeliveryStatus>?): List<Delivery> {
        statuses?.let {
            return deliveryRepository.findAllByStatusIn(statuses)
                .sortedBy { it.description }
        }

        return deliveryRepository.findAll()
            .toList()
    }

    override fun updateDeliveryState() {
        val deliveries = deliveryRepository.findAllByStatusIn(workingStatuses)
        log.info("Try to update {} deliveries", deliveries.size)

        deliveries.map {
            val deliveryInfo = deliveryClient.getDeliveryInfo(it.id)
            val delivery = deliveryMapper.map(deliveryInfo, it.id, it.orderNum)
            deliveryRepository.save(delivery)
            log.info("Delivery with id: {} updated", delivery.id)
        }
    }


}