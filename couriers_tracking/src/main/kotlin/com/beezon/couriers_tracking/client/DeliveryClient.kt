package com.beezon.couriers_tracking.client

import com.beezon.couriers_tracking.client.request.RouteRequest
import com.beezon.couriers_tracking.client.response.delivery.DeliveryInfoResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class DeliveryClient(
    @Value("\${client.delivery.url}")
    private val baseUrl: String
) {
    val client = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.ACCEPT_LANGUAGE, "ru")
        .build()

    fun getDeliveryInfo(id: String): DeliveryInfoResponse {
        val request = RouteRequest(id)
        return client.post()
            .uri("/4.0/cargo-c2c/v1/shared-route/info")
            .bodyValue(request)
            .retrieve()
            .bodyToMono(DeliveryInfoResponse::class.java)
            .block() ?: throw IllegalStateException("Response is null")
    }
}