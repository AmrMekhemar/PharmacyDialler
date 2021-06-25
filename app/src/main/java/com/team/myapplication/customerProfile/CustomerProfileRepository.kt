package com.team.myapplication.customerProfile

import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.register.model.Coordinates
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomerProfileRepository {
    suspend fun editCustomerName(name: String): Any
    suspend fun editCustomerPassword(editPasswordRequest: EditPasswordRequest): Any
    suspend fun editCustomerPhone(phone: String): Any
    suspend fun editCustomerAddress(address: String): Any
    suspend fun editCustomerCoordinates(coordinates: Coordinates): Any
    suspend fun editCustomerPhoto(encodedPhotoString: String)
}