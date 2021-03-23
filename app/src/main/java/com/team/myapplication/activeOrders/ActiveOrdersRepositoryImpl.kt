package com.team.myapplication.activeOrders

import com.team.myapplication.Order
import com.team.myapplication.RemoteApiService

class ActiveOrdersRepositoryImpl(private val apiService: RemoteApiService) : ActiveOrdersRepository {
    override suspend fun getActiveOrders(token: String): Any {
        return apiService.getCurrentOrders(token)
    }
}