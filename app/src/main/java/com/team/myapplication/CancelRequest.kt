package com.team.myapplication

data class CancelRequest(
    val orderId: String,
    val token: String
)