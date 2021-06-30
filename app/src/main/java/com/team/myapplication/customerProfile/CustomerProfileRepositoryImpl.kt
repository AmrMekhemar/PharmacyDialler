package com.team.myapplication.customerProfile

import com.team.myapplication.*
import com.team.myapplication.register.model.Coordinates

class CustomerProfileRepositoryImpl(private val apiService: RemoteApiService) :
    CustomerProfileRepository {
    override suspend fun editCustomerName(token: String, name: NameDataClass): MessageResponse {
        return apiService.editCustomerName(token, name)
    }

    override suspend fun editCustomerPassword(
        token: String,
        editPasswordRequest: EditPasswordRequest
    ): MessageResponse {
        return apiService.editCustomerPassword(token, editPasswordRequest)
    }

    override suspend fun editCustomerPhone(token: String, phone: PhoneDataClass): MessageResponse {
        return apiService.editCustomerPhone(token, phone)
    }

    override suspend fun editCustomerAddress(token: String, address: AddressDataClass): MessageResponse {
        return apiService.editCustomerAddress(token, address)
    }

    override suspend fun editCustomerCoordinates(token: String, coordinates: Coordinates): MessageResponse {
        return apiService.editCustomerCoordinates(token, coordinates)
    }

    override suspend fun editCustomerPhoto(token: String, photo : PhotoDataClass):MessageResponse {
        return apiService.editCustomerPhoto(token, photo)
    }
}