package com.team.myapplication.order

import androidx.lifecycle.ViewModel
import com.team.myapplication.NearestPharmacyRequest
import com.team.myapplication.register.model.RegisterReturnBody

class OrderViewModel(private val orderRepository: OrderRepository) : ViewModel() {
    suspend fun requestAnOrder(
        token: String,
        nearestPharmacyRequest: NearestPharmacyRequest
    ): RegisterReturnBody {
        return orderRepository.requestAnOrder(token, nearestPharmacyRequest)
    }
}