package com.team.myapplication.specificOrder

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class SpecificOrderViewModel : ViewModel(), KoinComponent {
    private val repo: SpecificOrderRepository by inject<SpecificOrderRepoImpl>()
    suspend fun getSpecificOrder(token: String,orderId:String): Any? {
        return repo.getSpecificOrder(token,orderId)
    }
}