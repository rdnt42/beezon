package com.beezon.couriers_tracking.enums

import com.fasterxml.jackson.annotation.JsonValue

enum class VisitStatus(@get: JsonValue val status: String) {
    PENDING("pending"),
    VISITED("visited"),
    ARRIVED("arrived"),
}