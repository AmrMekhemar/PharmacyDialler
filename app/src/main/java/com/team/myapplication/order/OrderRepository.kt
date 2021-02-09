package com.team.myapplication.order

import com.team.myapplication.NearestPharmacyRequest
import com.team.myapplication.register.model.RegisterReturnBody

interface OrderRepository {
    suspend fun requestAnOrder(
        token: String,
        nearestPharmacyRequest: NearestPharmacyRequest
    ): RegisterReturnBody
}