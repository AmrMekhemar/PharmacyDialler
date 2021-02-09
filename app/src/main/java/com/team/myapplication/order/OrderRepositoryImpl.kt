package com.team.myapplication.order

import com.team.myapplication.NearestPharmacyRequest
import com.team.myapplication.RemoteApiService
import com.team.myapplication.register.model.RegisterReturnBody

class OrderRepositoryImpl(private val apiService: RemoteApiService) : OrderRepository {
    override suspend fun requestAnOrder(
        token: String,
        nearestPharmacyRequest: NearestPharmacyRequest
    ): RegisterReturnBody {
        return apiService.getNearestPharmacy(token, nearestPharmacyRequest)
    }
}