package com.team.myapplication.customerProfile

import androidx.lifecycle.ViewModel
import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.MessageResponse
import com.team.myapplication.register.model.Coordinates

class CustomerProfileViewModel(private val repository: CustomerProfileRepository) :
    CustomerProfileRepository, ViewModel() {
    override suspend fun editCustomerName(token: String,name: String): MessageResponse {
        return repository.editCustomerName(token,name)
    }

    override suspend fun editCustomerPassword(token: String,editPasswordRequest: EditPasswordRequest): MessageResponse {
        return repository.editCustomerPassword(token,editPasswordRequest)
    }

    override suspend fun editCustomerPhone(token: String,phone: String): MessageResponse {
        return repository.editCustomerPhone(token,phone)
    }

    override suspend fun editCustomerAddress(token: String,address: String): MessageResponse {
        return repository.editCustomerAddress(token,address)
    }

    override suspend fun editCustomerCoordinates(token: String,coordinates: Coordinates): MessageResponse {
        return repository.editCustomerCoordinates(token,coordinates)
    }

    override suspend fun editCustomerPhoto(token: String,encodedPhotoString: String):MessageResponse {
        return repository.editCustomerPhoto(token,encodedPhotoString)
    }
}