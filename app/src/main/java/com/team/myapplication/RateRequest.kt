package com.team.myapplication

data class RateRequest(
    val orderId: String,
    val rate: Int,
    val token: String
)