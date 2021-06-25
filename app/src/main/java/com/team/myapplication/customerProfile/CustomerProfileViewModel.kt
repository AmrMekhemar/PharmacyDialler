package com.team.myapplication.customerProfile

import androidx.lifecycle.ViewModel
import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.register.model.Coordinates

class CustomerProfileViewModel(private val repository: CustomerProfileRepository) :
    CustomerProfileRepository, ViewModel() {
    override suspend fun editCustomerName(token: String,name: String): Any {
        return repository.editCustomerName(token,name)
    }

    override suspend fun editCustomerPassword(token: String,editPasswordRequest: EditPasswordRequest): Any {
        return repository.editCustomerPassword(token,editPasswordRequest)
    }

    override suspend fun editCustomerPhone(token: String,phone: String): Any {
        return repository.editCustomerPhone(token,phone)
    }

    override suspend fun editCustomerAddress(token: String,address: String): Any {
        return repository.editCustomerAddress(token,address)
    }

    override suspend fun editCustomerCoordinates(token: String,coordinates: Coordinates): Any {
        return repository.editCustomerCoordinates(token,coordinates)
    }

    override suspend fun editCustomerPhoto(token: String,encodedPhotoString: String) {
        return repository.editCustomerPhoto(token,encodedPhotoString)
    }
}