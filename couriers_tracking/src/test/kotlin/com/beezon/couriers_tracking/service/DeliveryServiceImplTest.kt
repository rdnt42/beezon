package com.beezon.couriers_tracking.service

import com.beezon.couriers_tracking.client.DeliveryClient
import com.beezon.couriers_tracking.entity.Delivery
import com.beezon.couriers_tracking.enums.DeliveryStatus
import com.beezon.couriers_tracking.repository.DeliveryRepository
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*

internal class DeliveryServiceImplTest {
    private val deliveryClient = mockk<DeliveryClient>()
    private val deliveryRepository = mockk<DeliveryRepository>()
    private val deliveryMapper = mockk<DeliveryMapper>()

    private val deliveryService: DeliveryService = DeliveryServiceImpl(deliveryClient, deliveryRepository, deliveryMapper)

    @Test
    fun `should return sorted deliveries`() {
        val first = "В пути за посылкой:  ~1 мин"
        val second = "В пути за посылкой:  ~20 мин"
        val third = "В пути за посылкой:  ~55 мин"
        val deliveries = listOf(
            getDeliveryWithDsc(second),
            getDeliveryWithDsc(third),
            getDeliveryWithDsc(first)
        )
        every { deliveryRepository.findAllByStatusIn(listOf(DeliveryStatus.WAITING)) } returns deliveries

        val result = deliveryService.getAllDeliveries(listOf(DeliveryStatus.WAITING))

        result.size shouldBe 3
        result[0].description shouldBe first
        result[1].description shouldBe second
        result[2].description shouldBe third
    }

    private fun getDeliveryWithDsc(dsc: String): Delivery {
        return Delivery(
            id = UUID.randomUUID().toString(),
            orderNum = null,
            description = dsc,
            status = DeliveryStatus.WAITING,
            performer = "",
            whoRequested = "",
            addressFrom = "",
            addressTo = "",
            commentTo = "",
            phoneFrom = "",
            phoneTo = "",
            createdDate = Instant.now(),
            updatedDate = Instant.now()
        )
    }
}