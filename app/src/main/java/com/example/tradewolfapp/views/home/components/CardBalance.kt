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

@Composable
fun CardBalance(
    viewModel: CoinsViewModel = viewModel(),
) {
    val coins by viewModel.coins.collectAsState()

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Blue.copy(alpha = 0.5f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.4f)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "My balance",
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(bottom = 18.dp)
            )
            Text(
                text = coins.sumOf { it.price }.formatCryptoValue(),
                color = Color.White,
                fontSize = 35.sp,
                fontWeight = FontWeight.W700,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(1f)
                    .padding(horizontal = 30.dp)
                    .shadow(
                        elevation = 24.dp,
                        clip = true,
                        ambientColor = Color.Black.copy(alpha = 1f),
                        spotColor = Color.Black.copy(alpha = 1f),
                    )
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ),

                ) {
                ActionItem(
                    icon = Icons.AutoMirrored.Filled.TrendingUp,
                    description = "Trending Up",
                    text = "Analytics",
                    onClick = { }
                )
                ActionItem(
                    icon = Icons.Filled.ArrowUpward,
                    description = "Arrow Upward",
                    text = "Sell Crypto",
                    onClick = { }
                )
                ActionItem(
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    description = "Arrow Right Alt",
                    text = "Send Crypto",
                    onClick = { }
                )
            }
        }
    }


