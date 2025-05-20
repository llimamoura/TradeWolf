package com.example.tradewolfapp.services

import com.example.tradewolfapp.model.CoinModel
import retrofit2.http.GET

interface RemoteServices {
    @GET("coins")
    suspend fun getCoins(): List<CoinModel>
}