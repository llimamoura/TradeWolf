package com.example.tradewolfapp.services

import com.example.tradewolfapp.model.CoinModel
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class ApiResponse(
    @SerializedName("result") val result: List<CoinModel>
)

interface RemoteServices {
    @GET("coins")
    suspend fun getCoins(): ApiResponse
}