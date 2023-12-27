package com.beezon.storage_manager.entity

import jakarta.persistence.*

@Table(name = "orders")
@Entity
data class Order(
    @Id
    val id: String,

    val client: String,

    val itemsCount: Int,

    @OneToMany(mappedBy = "order")
    val parts: List<OrderPart>? = null,

    @Column(name = "cell_id")
    val cellId: Long? = null,

    @OneToOne
    @JoinColumn(name = "cell_id", referencedColumnName = "id", insertable = false, updatable = false)
    val cell: Cell? = null,

    val dsc: String? = null,
)
