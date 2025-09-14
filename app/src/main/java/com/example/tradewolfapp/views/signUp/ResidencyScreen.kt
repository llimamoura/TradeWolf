package com.example.tradewolfapp.views.signUp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue

@Composable
fun ResidencyScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier =  Modifier.height(80.dp))

        
        Text(
            text = "Let´s Verify Your",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Identity",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth())

        
        
        Spacer(modifier = Modifier.height(18.dp))

        
        Text(
            text = "We are required to verify your identity before you can use the application. Your information will be encrypted and stored securely.",
            color = Color.Black,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(80.dp))

        
        MainButtonComponent(
            text = "Verify identity",
            onClick = {
                navController.navigate("residencyForm")
            },
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue
        )
    }
}
