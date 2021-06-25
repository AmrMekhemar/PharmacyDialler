package com.team.myapplication.customerProfile

import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.RemoteApiService
import com.team.myapplication.register.model.Coordinates

class CustomerProfileRepositoryImpl(private val apiService: RemoteApiService) :
    CustomerProfileRepository {
    override suspend fun editCustomerName(token: String, name: String): Any {
        return apiService.editCustomerAddress(token, name)
    }

    override suspend fun editCustomerPassword(
        token: String,
        editPasswordRequest: EditPasswordRequest
    ): Any {
        return apiService.editCustomerPassword(token, editPasswordRequest)
    }

    override suspend fun editCustomerPhone(token: String, phone: String): Any {
        return apiService.editCustomerPhone(token, phone)
    }

    override suspend fun editCustomerAddress(token: String, address: String): Any {
        return apiService.editCustomerAddress(token, address)
    }

    override suspend fun editCustomerCoordinates(token: String, coordinates: Coordinates): Any {
        return apiService.editCustomerCoordinates(token, coordinates)
    }

    override suspend fun editCustomerPhoto(token: String, encodedPhotoString: String) {
        return apiService.editCustomerPhoto(token, encodedPhotoString)
    }
}