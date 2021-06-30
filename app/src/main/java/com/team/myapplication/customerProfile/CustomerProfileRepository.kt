package com.team.myapplication.customerProfile

import com.team.myapplication.*
import com.team.myapplication.register.model.Coordinates

interface CustomerProfileRepository {
    suspend fun editCustomerName(
        token: String,
        name: NameDataClass
    ): MessageResponse

    suspend fun editCustomerPassword(
        token: String,
        editPasswordRequest: EditPasswordRequest
    ): MessageResponse

    suspend fun editCustomerPhone(
        token: String,
        phone: PhoneDataClass
    ): MessageResponse

    suspend fun editCustomerAddress(
        token: String,
        address: AddressDataClass
    ): MessageResponse

    suspend fun editCustomerCoordinates(token: String, coordinates: Coordinates): MessageResponse
    suspend fun editCustomerPhoto(token: String, photo : PhotoDataClass) : MessageResponse
}