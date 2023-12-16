package com.beezon.storage_manager.entity

import jakarta.persistence.*

@Table(name = "orders")
@Entity
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    val barCode: String,

    val client: String,

    val itemsCount: Int,

    val cellId: Long? = null,

    @OneToOne
    @JoinColumn(name = "cell_id", referencedColumnName = "id")
    val cell: Cell? = null,
)
