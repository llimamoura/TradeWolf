package com.example.tradewolfapp.views.auth.SingUp

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.LightGrayCard
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class Country(val flag: String, val country: String, val code: String)

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

@Composable
fun ResidencyForm(navController: NavController) {
    val context = LocalContext.current
    val countries = remember { loadCountriesFromAssets(context) }
    val imageLoader = rememberSvgImageLoader()

    var selectedCountry by remember { mutableStateOf(countries.firstOrNull() ?: Country("", "Select Country", "")) }
    var countryMenuExpanded by remember { mutableStateOf(false) }

    val verificationMethods = listOf("National identity card", "Passport", "Driver license")
    var selectedMethod by remember { mutableStateOf(verificationMethods.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 34.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = BlueLogo,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Proof of Residency",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { countryMenuExpanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(15.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, 
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (selectedCountry.flag.isNotEmpty()) {
                        AsyncImage(
                            model = selectedCountry.flag,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            imageLoader = imageLoader
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    Text(
                        text = selectedCountry.country,
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        color = BlueLogo
                    )
                    Icon(
                        Icons.Default.ArrowDropDown, 
                        contentDescription = null
                    )
                }
            }

            DropdownMenu(
                expanded = countryMenuExpanded,
                onDismissRequest = { countryMenuExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (country.flag.isNotEmpty()) {
                                    AsyncImage(
                                        model = country.flag,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        imageLoader = imageLoader
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                                Text("${country.country} (${country.code.uppercase()})")
                            }
                        },
                        onClick = {
                            selectedCountry = country
                            countryMenuExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(34.dp))

        Text(
            text = "Verification method",
            fontWeight = FontWeight.Bold,
            color = BlueLogo,
            fontSize = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = LightGrayCard)
        ) {
            Column(
                modifier = Modifier
                .padding(vertical = 6.dp)
            ) {
                verificationMethods.forEach { method ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedMethod = method }
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = method == selectedMethod,
                            onClick = { selectedMethod = method }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                        text = method, 
                        fontSize = 18.sp, 
                        color = Color.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        MainButtonComponent(
            text = "Continue",
            onClick = {},
            color = BlueLogo,
            colorText = Color.White
        )
    }
}
