package com.example.tradewolfapp.views.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.utils.formatCryptoValue
import com.example.tradewolfapp.viewModel.coins.CoinsViewModel
import com.example.tradewolfapp.views.components.CoinIcon
import androidx.compose.foundation.background 

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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(coins) { _, coin ->
            Card(
                modifier = Modifier
                    .size(width = 380.dp , height = 75.dp)
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
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
                    Box()
                    {
                        CoinIcon(
                            iconUrl = coin.icon,
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterStart)
                        )
                        
                        
                        
                        Column(
                            modifier = Modifier.offset(x = 50.dp)
                        ){
                            Text(
                                text = coin.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = DeepBlue
                            )
                            Box(
                                modifier = Modifier 
                                .background(
                                    color = DeepBlue,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 3.dp),
                            ) {
                                Text(
                                    text = coin.symbol,
                                    fontSize = 12.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            
                        }
                    }

                    Text(
                        text = "${coin.price.formatCryptoValue()}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = DeepBlue
                    )
                }
            }
        }
    }
}
