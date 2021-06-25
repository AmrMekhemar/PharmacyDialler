package com.team.myapplication.customerProfile

import androidx.lifecycle.ViewModel
import com.team.myapplication.EditPasswordRequest
import com.team.myapplication.register.model.Coordinates

class CustomerProfileViewModel(private val repository: CustomerProfileRepository) :
    CustomerProfileRepository, ViewModel() {
    override suspend fun editCustomerName(name: String): Any {
        return repository.editCustomerName(name)
    }

    override suspend fun editCustomerPassword(editPasswordRequest: EditPasswordRequest): Any {
        return repository.editCustomerPassword(editPasswordRequest)
    }

    override suspend fun editCustomerPhone(phone: String): Any {
        return repository.editCustomerPhone(phone)
    }

    override suspend fun editCustomerAddress(address: String): Any {
        return repository.editCustomerAddress(address)
    }

    override suspend fun editCustomerCoordinates(coordinates: Coordinates): Any {
        return repository.editCustomerCoordinates(coordinates)
    }

    override suspend fun editCustomerPhoto(encodedPhotoString: String) {
        return repository.editCustomerPhoto(encodedPhotoString)
    }
}