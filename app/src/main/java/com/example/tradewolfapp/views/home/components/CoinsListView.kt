package com.example.tradewolfapp.views.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradewolfapp.ui.theme.Green
import com.example.tradewolfapp.utils.formatCryptoValue
import com.example.tradewolfapp.viewModel.coins.CoinsViewModel
import com.example.tradewolfapp.views.components.CoinIcon
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import com.example.tradewolfapp.ui.theme.BlueLogo

@Composable
fun CoinsListView(
    modifier: Modifier = Modifier,
    viewModel: CoinsViewModel = viewModel(),
) {
    val coins by viewModel.coins.collectAsState()

    LazyColumn(
        modifier = modifier
        .fillMaxSize()
        .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)       
    ) {
        itemsIndexed(coins) { _, coin ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Gray.copy(alpha = 0.3f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CoinIcon(iconUrl = coin.icon, modifier = Modifier.size(40.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = coin.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = BlueLogo
                            )
                            Text(
                                text = coin.symbol,
                                fontSize = 12.sp,
                                color = Color.Black
                            )
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "$${coin.price.formatCryptoValue()}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = BlueLogo
                        )
                        Text(
                            text = "${coin.priceChangeH}%",
                            color = if (coin.priceChangeH >= 0) Green else Color.Red,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

