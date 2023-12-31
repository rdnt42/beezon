package com.beezon.storage_manager.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Table(name = "order_parts")
@Entity
data class OrderPart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val barcode: String,

    val partNum: Int,

    @Column(name = "order_id")
    val orderId: String,

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    val order: Order? = null,

    val dsc: String? = null,
)
