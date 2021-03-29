package com.team.myapplication

import com.team.myapplication.register.model.Coordinates
import com.team.myapplication.register.model.RegisterObject
import com.team.myapplication.register.model.RegisterReturnBody
import com.team.myapplication.specificOrder.CancelRequest
import com.team.myapplication.specificOrder.CancelResponse
import retrofit2.http.*

interface RemoteApiService {
    @POST("customerSignin")
    suspend fun logIn(@Body loginObject: LoginObject): LoginReturnBody

    @POST("customerSignup")
    suspend fun register(@Body registerObject: RegisterObject): RegisterReturnBody

    @GET("customerOrderHistory")
    suspend fun getOrderHistory(@Header("token") token: String): OrderHistoryResponse

    @GET("customerCurrentOrders")
    suspend fun getCurrentOrders(@Header("token") token: String): OrderHistoryResponse
    // Accepted or not accepted
    @GET("currentOrder/{orderId}")
    suspend fun getSpecificOrder(
        @Header("token") token: String,
        @Path("orderId") orderId: Any
    ): SpecificOrderResponse
    // Still Active
    @GET("customerCurrentOrders/{orderId}")
    suspend fun getSpecificActiveOrder(
        @Header("token") token: String,
        @Path("orderId") orderId: Any
    ): SpecificOrderResponse
    @POST("cancel")
    suspend fun cancelOrder( @Header("token") token: String,
                             @Body cancelRequest: CancelRequest
    ): CancelResponse
    @POST("rate")
    suspend fun sendRate(@Body rateObject: RateRequest): Any

    @POST("reportProblem")
    suspend fun reportProblem(@Body reportProblem: ReportProblemRequest): Any

    @POST("forgotPasswordCustomer")
    suspend fun passwordResetRequest(@Body email: String): Any

    @POST("getNearestPharmacy")
    suspend fun getNearestPharmacy(
        @Header("token") token: String,
        @Body nearestPharmacyRequest: NearestPharmacyRequest
    ): RegisterReturnBody

    @GET("customerCurrent")
    suspend fun getCurrentCustomer(@Body currentCustomerRequest: CurrentCustomerRequest): Any

    @GET("ourPharmacies")
    suspend fun getRegisteredPharmacies(@Body token: String): Any



    // Profile Edit
    @POST("editCustomerName")
    suspend fun editCustomerName(@Body name: String): Any


    @POST("editCustomerPass")
    suspend fun editCustomerPassword(@Body editPasswordRequest: EditPasswordRequest): Any

    @POST("editCustomerPhone")
    suspend fun editCustomerPhone(@Body phone: String): Any

    @POST("editCustomerAddress")
    suspend fun editCustomerAddress(@Body address: String): Any

    @POST("editCustomerCoordinates")
    suspend fun editCustomerCoordinates(@Body coordinates: Coordinates): Any

    @POST("editCustomerPhoto")
    suspend fun editCustomerPhoto(@Body photo: String)
}