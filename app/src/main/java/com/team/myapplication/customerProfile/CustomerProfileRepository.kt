package com.team.myapplication.customerProfile

import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.MessageResponse
import com.team.myapplication.register.model.Coordinates
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CustomerProfileRepository {
    suspend fun editCustomerName(
        token: String,
        name: String
    ): MessageResponse

    suspend fun editCustomerPassword(
        token: String,
        editPasswordRequest: EditPasswordRequest
    ): MessageResponse

    suspend fun editCustomerPhone(
        token: String,
        phone: String
    ): MessageResponse

    suspend fun editCustomerAddress(
        token: String,
        address: String
    ): MessageResponse

    suspend fun editCustomerCoordinates(token: String, coordinates: Coordinates): MessageResponse
    suspend fun editCustomerPhoto(token: String, encodedPhotoString: String) : MessageResponse
}