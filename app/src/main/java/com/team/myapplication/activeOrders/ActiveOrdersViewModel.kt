package com.team.myapplication.activeOrders

import androidx.lifecycle.ViewModel
import com.team.myapplication.Order
import org.koin.core.KoinComponent
import org.koin.core.inject

class ActiveOrdersViewModel: ViewModel() , KoinComponent {
    private val activeOrdersRepository  by inject<ActiveOrdersRepositoryImpl>()
    suspend fun getOrderHistory(
        token: String
    ):Any {
        return activeOrdersRepository.getActiveOrders(token)
    }
}