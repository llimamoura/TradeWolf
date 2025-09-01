package com.example.tradewolfapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.views.components.MainButtonComponent

@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueLogo),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.size(250.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp, vertical = 30.dp)
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "Welcome to,",

                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "TradeWolf",
                fontSize = 40.sp,
                color = Color.White,
                letterSpacing = 0.1.em,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Hunt for the best prices on your favorite Blockchain",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(70.dp))

            MainButtonComponent(
                text = "Get Started",
                onClick = { navController.navigate("loginScreen") },
                colorText = Color.White,
                colorStart = DeepBlue,
                colorEnd = CobaltBlue
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController = navController)
}