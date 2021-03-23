package com.team.myapplication

data class OrderHistoryResponse(
    val customerOrders: List<Order>?,
    val message: String
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
    val status: String
)

data class Pharmacy(
    val _id: String,
    val id: String,
    val status: String
)

