package com.example.tradewolfapp.views.signUp

import androidx.compose.foundation.background
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
import coil.compose.AsyncImage
import com.example.tradewolfapp.model.Country
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.utils.loadCountriesFromAssets
import com.example.tradewolfapp.utils.rememberSvgImageLoader
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.R

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
            modifier = Modifier.fillMaxWidth()
            .padding( vertical = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            
        ) {
             Box(
                modifier  = Modifier
                .size(48.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.09f),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { navController.popBackStack() },
                contentAlignment  = Alignment.Center
            ){
                Image(
                painter  = painterResource(id = R.drawable.back_icon),
                contentDescription  = "Back",
                modifier = Modifier
                .size(38.dp)
                )
            }
        }

        Text(
            text = "Proof of Residency",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { countryMenuExpanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(15.dp)
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

                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
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

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "Verification method",
            fontWeight = FontWeight.Bold,
            color = BlueLogo,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD3D3D3))
        ) {
            Column {
                verificationMethods.forEachIndexed { index, method ->
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
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    if (index < verificationMethods.lastIndex) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            color = Color.Gray.copy(alpha = 0.5f),
                            thickness = 1.dp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(65.dp))

        MainButtonComponent(
            text = "Continue",
            onClick = {
                navController.navigate("createAccount")
             },
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue
        )
    }
}
