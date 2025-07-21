package com.example.tradewolfapp.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.DarkGray
import com.example.tradewolfapp.utils.setTime
import com.example.tradewolfapp.viewModel.LoginWithGoogleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    userViewModel: LoginWithGoogleViewModel,
) {
    val user by userViewModel.user.collectAsState()
    val userPhoto = user?.photoUrl
    val setTime = setTime()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(text = setTime, color = Color.Gray, fontSize = 16.sp)
                        Text(
                            text = user?.displayName ?: "username",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DarkGray,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    if (userPhoto != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = userPhoto),
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
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "notifications",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkGray)
                .padding(innerPadding)
                .padding(horizontal = 18.dp),
        ) {
            HomeContent()
        }
    }
}