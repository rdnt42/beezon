package com.beezon.couriers_tracking.service

import com.beezon.couriers_tracking.client.response.delivery.DeliveryInfoResponse
import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus
import com.beezon.couriers_tracking.enums.VisitStatus
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class DeliveryMapper {
    fun map(response: DeliveryInfoResponse, id: String, orderNum: String?): Delivery {
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
        if ((srcStatus == VisitStatus.PENDING || srcStatus == VisitStatus.ARRIVED) && dstStatus == VisitStatus.PENDING) {
            return DeliveryStatus.WAITING
        } else if (srcStatus == VisitStatus.VISITED && dstStatus == VisitStatus.PENDING) {
            return DeliveryStatus.IN_PROCESS
        } else if (srcStatus == VisitStatus.VISITED && (dstStatus == VisitStatus.VISITED || dstStatus == VisitStatus.ARRIVED)) {
            return DeliveryStatus.COMPLETED
        }

        throw UnsupportedOperationException("Got unexpected state for statuses src: $srcStatus and dst $dstStatus")
    }
}