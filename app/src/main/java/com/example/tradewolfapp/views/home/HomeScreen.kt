package com.example.tradewolfapp.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.viewModel.auth.LoginWithGoogleViewModel
import com.example.tradewolfapp.viewModel.coins.CoinsViewModel
import com.example.tradewolfapp.views.home.components.CardBalance
import com.example.tradewolfapp.views.home.components.CoinsListView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userViewModel: LoginWithGoogleViewModel,
    coinsViewModel: CoinsViewModel
) {
    val user by userViewModel.user.collectAsState()
    val isLoading by coinsViewModel.isLoading.collectAsState()
    val isSuccess by coinsViewModel.isSuccess.collectAsState()
    val error by coinsViewModel.error.collectAsState()
    val userPhoto = user?.photoUrl
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        coinsViewModel.loadCoins()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.logohome),
                        contentDescription = "App Logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(76.dp)
                    )
                },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                    IconButton(onClick = {navController.navigate("search")}) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_home ),
                            contentDescription = "Search",
                            tint = Color(0xFF00234F),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.belldot_home),
                            contentDescription = "Notifications",
                            tint = Color(0xFF00234F),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    
                    if (userPhoto != null) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context)
                                    .data(userPhoto)
                                    .apply {
                                        error(R.drawable.logo)
                                        placeholder(R.drawable.logo)
                                    }
                                    .build()
                            ),
                            contentDescription = "Profile photo",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(36.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        IconButton(onClick = { /* fallback */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Default logo",
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            },
               colors = TopAppBarDefaults.topAppBarColors(
                     containerColor = Color.White,
                     titleContentColor = Color.Black
               )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.Center
        ) {
            when {
                isLoading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = BlueLogo)
                    }
                }

                error != null -> {
                    Text(text = error ?: "", color = Color.Red)
                }

                isSuccess -> {
                    Spacer(Modifier.height(25.dp))
                    CardBalance()
                    Text(
                        text = "Your assets",
                        color = BlueLogo,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(bottom = 10.dp, top = 40.dp)
                    )
                    CoinsListView()
                }
            }
        }
    }
}
