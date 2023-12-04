package com.beezon.couriers_tracking.client.response.delivery

import com.fasterxml.jackson.annotation.JsonProperty

data class Performer(
    @JsonProperty("vehicle_number")
    val vehicleNumber: String?
)
