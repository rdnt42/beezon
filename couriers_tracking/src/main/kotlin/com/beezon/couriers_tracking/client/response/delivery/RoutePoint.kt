package com.beezon.couriers_tracking.client.response.delivery

import com.beezon.couriers_tracking.enums.RoutePointType
import com.beezon.couriers_tracking.enums.VisitStatus
import com.beezon.couriers_tracking.client.response.Contact
import com.fasterxml.jackson.annotation.JsonProperty

data class RoutePoint(
    @JsonProperty("visit_status")
    val status: VisitStatus,
    @JsonProperty("full_text")
    val address: String,
    val contact: Contact?,
    val comment: String?,
    val type: RoutePointType
)
