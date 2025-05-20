package com.example.tradewolfapp.model

import com.google.gson.annotations.SerializedName

data class CoinModel(
    val id: String,
    val icon: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val price: Double,
    @SerializedName("priceBtc")
    val priceBtc: Double,
    val volume: Double,
    @SerializedName("marketCap")
    val marketCap: Double,
    val availableSupply: Int,
    val totalSupply: Int,
    @SerializedName("fullyDilutedValuation")
    val fullyDilutedValuation: Double,
    @SerializedName("priceChange1h")
    val priceChange1H: Double,
    @SerializedName("priceChange1d")
    val priceChange1D: Double,
    @SerializedName("priceChange1w")
    val priceChange1W: Double,
    val redditUrl: String?,
    val twitterUrl: String?,
    val explorers: List<String>
)
