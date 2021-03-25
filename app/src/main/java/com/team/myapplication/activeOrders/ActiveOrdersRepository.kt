package com.team.myapplication.activeOrders

import com.team.myapplication.Order
import com.team.myapplication.OrderHistoryResponse

interface ActiveOrdersRepository {
    suspend fun getActiveOrders(
        token: String
    ): OrderHistoryResponse
}