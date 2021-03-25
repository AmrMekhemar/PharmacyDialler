package com.team.myapplication.specificOrder

import retrofit2.http.Path

interface SpecificOrderRepository {
    suspend fun getSpecificOrder(token: String,orderId:String): Any
}