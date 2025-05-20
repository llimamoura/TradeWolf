package com.example.tradewolfapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tradewolfapp.model.CoinModel
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.viewModel.CoinsViewModel
import com.example.tradewolfapp.viewModel.LoginViewModel

@Composable
fun HomeScreen (
    navController: NavController,
    viewModel: CoinsViewModel = viewModel()
) {
    val coins by viewModel.coins.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.loadCoinsIfNeeded()
    }


    LazyColumn {
        items(coins) { coin ->
            Text(text = coin.name, color = Color.Black)
        }
    }

}