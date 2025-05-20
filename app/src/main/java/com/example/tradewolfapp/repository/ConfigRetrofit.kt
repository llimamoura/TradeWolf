package com.example.tradewolfapp.repository

import com.example.tradewolfapp.services.RemoteServices
import com.example.tradewolfapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ConfigRetrofit {
    private val BASE_URL = "https://openapiv1.coinstats.app/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }



    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("X-API-KEY", BuildConfig.API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()


    val services: RemoteServices by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteServices::class.java)
    }

}