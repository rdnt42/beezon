package com.beezon.couriers_tracking.client.response.delivery

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DeliveryInfoResponse(
    val summary: String,
    val description: String,
    @JsonProperty("route_points")
    val routePoints: List<RoutePoint>,
    val performer: Performer
)
