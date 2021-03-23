package com.team.myapplication.ordersHistory

import com.team.myapplication.Order
import com.team.myapplication.OrderHistoryResponse

interface OrderHistoryRepository {
    suspend fun getOrderHistory(
        token: String
    ): OrderHistoryResponse
}