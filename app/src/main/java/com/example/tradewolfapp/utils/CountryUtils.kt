package com.example.tradewolfapp.utils

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.tradewolfapp.model.Country
import androidx.compose.ui.platform.LocalContext

fun loadCountriesFromAssets(context: Context): List<Country> {
    return try {
        val json = context.assets.open("countries-flags.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Country>>() {}.type
        Gson().fromJson(json, type)
    } catch (e: Exception) {
        e.printStackTrace()
        emptyList()
    }
}

@Composable
fun rememberSvgImageLoader(context: Context = LocalContext.current): ImageLoader {
    return remember {
        ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }
}
