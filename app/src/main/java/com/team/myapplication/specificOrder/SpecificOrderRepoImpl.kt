package com.team.myapplication.specificOrder

import com.team.myapplication.RemoteApiService

class SpecificOrderRepoImpl(private val apiService: RemoteApiService):SpecificOrderRepository {
    override suspend fun getSpecificOrder(token: String,orderId:String): Any {
      return  apiService.getSpecificOrder(token,orderId)
    }
}