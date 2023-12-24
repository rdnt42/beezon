package com.beezon.storage_manager.dto.request

data class OrderPartRequest(
    val barCode: String,

    val partNum: Int,

    val orderId: String,

    val cellId: Long,

    val dsc: String?,
)
