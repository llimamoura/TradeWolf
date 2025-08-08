package com.example.tradewolfapp.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.tradewolfapp.utils.setTime
import com.example.tradewolfapp.viewModel.auth.LoginWithGoogleViewModel
import com.example.tradewolfapp.views.home.components.CardBalance
import com.example.tradewolfapp.views.home.components.CoinsListView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userViewModel: LoginWithGoogleViewModel,
) {
    val user by userViewModel.user.collectAsState()
    val userPhoto = user?.photoUrl
    val setTime = setTime()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column (modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(text = setTime, color = Color.Gray, fontSize = 16.sp)
                        Text(
                            text = user?.displayName ?: "username",
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                navigationIcon = {
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
                                .padding(start = 12.dp)
                                .size(50.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        IconButton(onClick = { /*fallback*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "logo"
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "notifications",
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
        ) {
            Spacer(Modifier.height(25.dp))
            CardBalance()
            Text(
                text = "My Portfolio",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp, top = 50.dp)
            )
            CoinsListView()
        }
    }
}