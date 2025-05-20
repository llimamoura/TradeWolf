package com.example.tradewolfapp.model

import com.google.gson.annotations.SerializedName

data class CoinModel(
    val id: String,
    val icon: String,
    @SerializedName("name")
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    val priceBtc: Double,
    val volume: Double,
    val marketCap: Double,
    val availableSupply: Long,
    val totalSupply: Long,
    val fullyDilutedValuation: Double,
    @SerializedName("priceChange1h")
    val priceChangeH: Double,
    @SerializedName("priceChange1d")
    val priceChangeD: Double,
    @SerializedName("priceChange1w")
    val priceChangeW: Double,
    val redditUrl: String?,
    val twitterUrl: String?,
    val explorers: List<String>
)
