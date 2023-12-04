package com.beezon.couriers_tracking.controller.request

data class CreateDeliveryRequest(
    val url: String,
    val orderNum: String?,
)
