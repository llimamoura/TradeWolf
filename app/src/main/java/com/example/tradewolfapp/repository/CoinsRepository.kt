package com.example.tradewolfapp.repository

import android.util.Log
import com.example.tradewolfapp.model.CoinModel
import com.example.tradewolfapp.services.RemoteServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoinsRepository (private val service: RemoteServices ){
    suspend fun fetchCoins(): List<CoinModel> = withContext(Dispatchers.IO) {
        try {
           val response = service.getCoins()
            Log.d("REPOSITORY_DEBUG", "Dados brutos: ${response.result}")
            response.result
        } catch (e: Exception) {
            Log.e("REPOSITORY", "Erro: ${e.message}", e)
            emptyList()
        }
    }
}