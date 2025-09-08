package com.example.tradewolfapp.views.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradewolfapp.ui.theme.MidnightBlue
import com.example.tradewolfapp.utils.formatCryptoValue
import com.example.tradewolfapp.viewModel.coins.CoinsViewModel
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.graphics.Brush
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue



@Composable
fun CardBalance(
    viewModel: CoinsViewModel = viewModel(),
) {
    val coins by viewModel.coins.collectAsState()

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.4f)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF00224F),
                        Color(0xFF004EB5)
                    )
                ),
                shape = RoundedCornerShape(20.dp)            
                )

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Hello, Fernando!",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp ,bottom = 18.dp)
            )

            Text(
                text = " My balance",
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 18.dp)
            )
            
            Text(
                text = coins.sumOf { it.price }.formatCryptoValue(),
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
 
        }
    }


