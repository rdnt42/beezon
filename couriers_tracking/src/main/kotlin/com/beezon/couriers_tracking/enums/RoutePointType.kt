package com.beezon.couriers_tracking.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class RoutePointType(@get: JsonValue val type: String) {
    SOURCE("source"),
    DESTINATION("destination")
}