package com.team.myapplication.specificOrder

import com.team.myapplication.RemoteApiService
import com.team.myapplication.SpecificOrderResponse

class SpecificOrderRepoImpl(private val apiService: RemoteApiService):SpecificOrderRepository {
    override suspend fun getSpecificOrder(token: String,orderId:String): SpecificOrderResponse {
      return  apiService.getSpecificOrder(token,orderId)
    }

    override suspend fun cancelOrder(token: String, cancelRequest: CancelRequest): CancelResponse {
        return apiService.cancelOrder(token,cancelRequest)
    }
}