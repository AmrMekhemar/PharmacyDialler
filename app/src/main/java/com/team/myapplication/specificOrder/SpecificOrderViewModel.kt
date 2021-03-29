package com.team.myapplication.specificOrder

import androidx.lifecycle.ViewModel
import com.team.myapplication.SpecificOrderResponse
import org.koin.core.KoinComponent
import org.koin.core.inject

class SpecificOrderViewModel : ViewModel(), KoinComponent {
    private val repo: SpecificOrderRepository by inject<SpecificOrderRepoImpl>()
    suspend fun getSpecificOrder(token: String,orderId:String): SpecificOrderResponse {
        return repo.getSpecificOrder(token,orderId)
    }
    suspend fun cancelOrder( token: String,
                             cancelRequest: CancelRequest
    ): CancelResponse {
        return repo.cancelOrder(token,cancelRequest)
    }
}