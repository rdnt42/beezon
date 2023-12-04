package com.beezon.couriers_tracking.entity

import com.beezon.couriers_tracking.enums.DeliveryStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@Table(name = "deliveries")
@Entity
class Delivery(
    @Id
    val id: String,
    val orderNum: String?,

    @Column
    val description: String,
    @Column
    val status: DeliveryStatus,
    @Column
    val performer: String,
    @Column
    val whoRequested: String,

    @Column
    val addressFrom: String,
    @Column
    val phoneFrom: String?,

    @Column
    val addressTo: String,
    @Column
    val phoneTo: String?,
    @Column
    val commentTo: String?,

    @CreatedDate
    val createdDate: Instant,
    @LastModifiedDate
    val updatedDate: Instant
    )