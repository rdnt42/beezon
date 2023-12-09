package com.beezon.couriers_tracking.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "users")
@Entity
class User(
    @Id
    val username: String,

    val password: String,
)