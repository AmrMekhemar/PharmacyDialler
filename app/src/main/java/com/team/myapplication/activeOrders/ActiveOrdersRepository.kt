package com.team.myapplication.activeOrders

import com.team.myapplication.Order

interface ActiveOrdersRepository {
    suspend fun getActiveOrders(
        token: String
    ): Any
}