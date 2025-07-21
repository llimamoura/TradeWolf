
package com.example.tradewolfapp.views.home

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradewolfapp.utils.formatCryptoValue
import com.example.tradewolfapp.viewModel.CoinsViewModel
import com.example.tradewolfapp.views.components.CoinIcon

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: CoinsViewModel = viewModel()
) {
    val coins by viewModel.coins.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCoinsIfNeeded()
    }

    Column(modifier = modifier) {

        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){

            itemsIndexed(coins) { index, coin ->
                if (index > 0) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            CoinIcon(iconUrl = coin.icon, modifier = Modifier.size(40.dp))
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(text = coin.symbol, color = Color.White)
                                Text(text = coin.name, color = Color.Gray)
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(text = coin.price.formatCryptoValue(), color = Color.White)
                            Text(
                                text = "${coin.priceChangeH}",
                                color = if (coin.priceChangeH >= 0) Color.Green else Color.Red
                            )
                        }
                    }
                }
            }
        }
    }

}
