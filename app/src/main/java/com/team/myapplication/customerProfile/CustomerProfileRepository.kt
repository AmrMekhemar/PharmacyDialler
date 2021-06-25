package com.team.myapplication.customerProfile

import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.register.model.Coordinates
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CustomerProfileRepository {
    suspend fun editCustomerName(
        token: String,
        name: String
    ): Any

    suspend fun editCustomerPassword(
        token: String,
        editPasswordRequest: EditPasswordRequest
    ): Any

    suspend fun editCustomerPhone(
        token: String,
        phone: String
    ): Any

    suspend fun editCustomerAddress(
        token: String,
        address: String
    ): Any

    suspend fun editCustomerCoordinates(token: String, coordinates: Coordinates): Any
    suspend fun editCustomerPhoto(token: String, encodedPhotoString: String)
}