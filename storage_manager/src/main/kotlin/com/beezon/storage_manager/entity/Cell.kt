package com.beezon.storage_manager.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Table(name = "cells")
@Entity
data class Cell(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val placeCode: String,

    @JsonIgnore
    @OneToOne(mappedBy="cell")
    val orders: Order
)
