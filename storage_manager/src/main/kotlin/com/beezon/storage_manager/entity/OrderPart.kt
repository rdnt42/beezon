package com.beezon.storage_manager.entity

import jakarta.persistence.*

@Table(name = "order_parts")
@Entity
data class OrderPart(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val barCode: String,

    val partNum: Int,

    @Column(name = "order_id")
    val orderId: String,

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", insertable = false, updatable = false)
    val order: Order? = null,

    val dsc: String? = null,

)
