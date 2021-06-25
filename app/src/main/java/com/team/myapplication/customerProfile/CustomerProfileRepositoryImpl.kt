package com.team.myapplication.customerProfile

import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.MessageResponse
import com.team.myapplication.RemoteApiService
import com.team.myapplication.register.model.Coordinates

class CustomerProfileRepositoryImpl(private val apiService: RemoteApiService) :
    CustomerProfileRepository {
    override suspend fun editCustomerName(token: String, name: String): MessageResponse {
        return apiService.editCustomerAddress(token, name)
    }

    override suspend fun editCustomerPassword(
        token: String,
        editPasswordRequest: EditPasswordRequest
    ): MessageResponse {
        return apiService.editCustomerPassword(token, editPasswordRequest)
    }

    override suspend fun editCustomerPhone(token: String, phone: String): MessageResponse {
        return apiService.editCustomerPhone(token, phone)
    }

    override suspend fun editCustomerAddress(token: String, address: String): MessageResponse {
        return apiService.editCustomerAddress(token, address)
    }

    override suspend fun editCustomerCoordinates(token: String, coordinates: Coordinates): MessageResponse {
        return apiService.editCustomerCoordinates(token, coordinates)
    }

    override suspend fun editCustomerPhoto(token: String, encodedPhotoString: String):MessageResponse {
        return apiService.editCustomerPhoto(token, encodedPhotoString)
    }
}