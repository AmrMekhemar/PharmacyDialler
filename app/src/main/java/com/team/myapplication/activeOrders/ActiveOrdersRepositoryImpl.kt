package com.team.myapplication.activeOrders

import com.team.myapplication.Order
import com.team.myapplication.OrderHistoryResponse
import com.team.myapplication.RemoteApiService

class ActiveOrdersRepositoryImpl(private val apiService: RemoteApiService) : ActiveOrdersRepository {
    override suspend fun getActiveOrders(token: String): OrderHistoryResponse {
        return apiService.getCurrentOrders(token)
    }
}