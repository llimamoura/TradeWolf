package com.example.tradewolfapp.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.Poppins
import com.example.tradewolfapp.views.components.MainButtonComponent

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        CobaltBlue,
                        BlueLogo
                    )
                )
            )
    ) {

        
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF77B1FF).copy(alpha = 0.5f),
                        Color(0xFF77B1FF).copy(alpha = 0.4f),
                        Color(0xFF77B1FF).copy(alpha = 0.3f),
                        Color(0xFF77B1FF).copy(alpha = 0.2f),
                        Color(0xFF77B1FF).copy(alpha = 0.1f),
                        Color.Transparent
                    ),
                    center = Offset(200f, 150f),
                    radius = 500f
                ),
                center = Offset(200f, 150f),
                radius = 500f
            )
        }

        
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF77B1FF).copy(alpha = 0.5f),
                        Color(0xFF77B1FF).copy(alpha = 0.4f),
                        Color(0xFF77B1FF).copy(alpha = 0.3f),
                        Color(0xFF77B1FF).copy(alpha = 0.2f),
                        Color(0xFF77B1FF).copy(alpha = 0.1f),
                        Color.Transparent
                    ),
                    center = Offset(size.width - 50f, 500f),
                    radius = 600f
                ),
                center = Offset(size.width - 60f, 500f),
                radius = 600f
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "logo",
                modifier = Modifier.size(260.dp)
            )

            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp)
            ) {
                Text(
                    text = "Welcome to,",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "TradeWolf",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 37.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Hunt for the best prices on your favorite Blockchain",
                    style = TextStyle(
                        fontFamily = Poppins,
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Light
                    )
                )

                Spacer(modifier = Modifier.height(70.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color(0x60FFFFFF),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(3.dp)
                ) {
                    MainButtonComponent(
                        text = "Get started",
                        onClick = { navController.navigate("loginScreen") },
                        colorText = Color.White,
                        colorStart = DeepBlue,
                        colorEnd = CobaltBlue
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun WelcomeScreenPreview() {
    val navController = rememberNavController()
    WelcomeScreen(navController = navController)
}