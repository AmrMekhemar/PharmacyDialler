package com.team.myapplication.ordersHistory

import androidx.lifecycle.ViewModel
import com.team.myapplication.NearestPharmacyRequest
import com.team.myapplication.Order
import com.team.myapplication.OrderHistoryResponse
import com.team.myapplication.register.model.RegisterReturnBody
import org.koin.core.KoinComponent
import org.koin.core.inject

class OrderHistoryViewModel: ViewModel() , KoinComponent {
    val orderHistoryRepository  by inject<OrderHistoryImpl>()
    suspend fun getOrderHistory(
        token: String
    ): OrderHistoryResponse {
        return orderHistoryRepository.getOrderHistory(token)
    }
}