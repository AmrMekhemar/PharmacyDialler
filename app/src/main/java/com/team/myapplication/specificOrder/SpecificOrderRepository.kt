package com.team.myapplication.specificOrder

import com.team.myapplication.SpecificOrderResponse

interface SpecificOrderRepository {
    suspend fun getSpecificOrder(token: String,orderId:String): SpecificOrderResponse
    suspend fun cancelOrder( token: String,
                             cancelRequest: CancelRequest
    ): CancelResponse
}