package com.example.tradewolfapp.views.auth.SingUp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.views.components.MainButtonComponent

@Composable
fun ResidencyScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(80.dp))
        
        Text(
            text = "Let's Verify Your",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Identity",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "We are required to verify your identity before you can use the application. Your information will be encrpyted and stored securely.",
            color = Color.Gray,
            fontSize = 15.sp,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(60.dp))

        MainButtonComponent(
            text = "Verify identity",
            onClick = {
                navController.navigate("residencyForm")
            },
            color = BlueLogo,
            colorText = Color.White
        )
    }
}