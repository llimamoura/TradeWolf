package com.example.tradewolfapp.views.search


import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.DullGray
import com.example.tradewolfapp.ui.theme.SoftBlue
import com.example.tradewolfapp.utils.formatCryptoValue
import com.example.tradewolfapp.viewModel.coins.CoinsViewModel
import com.example.tradewolfapp.views.components.CoinIcon
import com.example.tradewolfapp.views.search.components.CustomSearchBar

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: CoinsViewModel = viewModel()
) {
    val query by viewModel.query.collectAsState()
    val results by viewModel.filterCoins.collectAsState()
    var active by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCoins()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to Color.White,
                        0.8f to Color.White,
                        1.0f to DullGray.copy(alpha = 0.5f)
                    )
                )
            )
            .padding(horizontal = 22.dp)
    ) {
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.09f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back",
                    modifier = Modifier.size(38.dp)
                )
            }
        }
        
        Text(
            text = "Search for currencies",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = Brush.verticalGradient(
                    colors = listOf(DeepBlue, CobaltBlue)
                )
            )
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "and services",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                brush = Brush.verticalGradient(
                    colors = listOf(DeepBlue, CobaltBlue)
                )
            )
        )

        Spacer(modifier = Modifier.height(66.dp))

        
        CustomSearchBar(
            query = query,
            onQueryChange = { newQuery ->
                viewModel.updateQuery(newQuery)
                active = newQuery.isNotBlank()
            },
            onSearch = { },
            active = active,
            onActiveChange = { active = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Search", color = Color.Gray, fontWeight = FontWeight.ExtraBold)
            }
        ) {
            if (results.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SoftBlue)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        items(results) { coin ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                CoinIcon(iconUrl = coin.icon, modifier = Modifier.size(28.dp))
                                    
                                Text(
                                    text = "${coin.name} (${coin.symbol})",
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                                    
                                Text(
                                    text = coin.price.formatCryptoValue(),
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}