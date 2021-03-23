package com.team.myapplication.ordersHistory

import com.team.myapplication.Order
import com.team.myapplication.OrderHistoryResponse
import com.team.myapplication.RemoteApiService

class OrderHistoryImpl(private val apiService: RemoteApiService) :OrderHistoryRepository {
    override suspend fun getOrderHistory(token: String): OrderHistoryResponse {
        return apiService.getOrderHistory(token)
    }
}