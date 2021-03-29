package com.team.myapplication

import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.register.model.LocationAsCoordinates

data class OrderHistoryResponse(
    val customerOrders: List<Order>?,
    val message: String
)

data class SpecificOrderResponse(
    val orderData: Order,
    val message: String,
    val pharmacyData :PharmacyData
)

data class Order(
    val _id: String,
    val customerID: String,
    val date: String,
    val globalStatus: String,
    val orderByPhoto: String?,
    val orderByTexting: String?,
    val pharmaciesID: List<PharmacyID>,
    val rate: String,
    val report: String?
)


data class PharmacyID(
    val Object: List<Pharmacy>,
    val status: String,
    var  __v: Double = 0.0
)

data class Pharmacy(
    val _id: String,
    val id: String,
    val status: String
)
data class PharmacyData(
   val name:String,
   val phones : List<String>,
   val logo :String,
   val rate : String,
   val locationAsAddress:String,
   val locationAsCoordinates : LocationAsCoordinates
)

