package com.team.myapplication.customerProfile

import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.RemoteApiService
import com.team.myapplication.register.model.Coordinates

class CustomerProfileRepositoryImpl(private val apiService: RemoteApiService) :CustomerProfileRepository {
    override suspend fun editCustomerName(name: String): Any {
       return apiService.editCustomerAddress(name)
    }

    override suspend fun editCustomerPassword(editPasswordRequest: EditPasswordRequest): Any {
        return apiService.editCustomerPassword(editPasswordRequest)
    }

    override suspend fun editCustomerPhone(phone: String): Any {
        return apiService.editCustomerPhone(phone)
    }

    override suspend fun editCustomerAddress(address: String): Any {
        return apiService.editCustomerAddress(address)
    }

    override suspend fun editCustomerCoordinates(coordinates: Coordinates): Any {
        return apiService.editCustomerCoordinates(coordinates)
    }

    override suspend fun editCustomerPhoto(encodedPhotoString: String) {
        return apiService.editCustomerPhoto(encodedPhotoString)
    }
}