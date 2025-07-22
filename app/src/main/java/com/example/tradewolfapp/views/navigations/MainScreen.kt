package com.example.tradewolfapp.views.navigations


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.viewModel.LoginWithGoogleViewModel
import com.example.tradewolfapp.viewModel.LoginWithGoogleViewModelFactory
import com.example.tradewolfapp.views.home.HomeScreen
import com.example.tradewolfapp.views.market.MarketScreen
import com.example.tradewolfapp.views.profile.ProfileScreen
import com.example.tradewolfapp.views.transaction.TransactionScreen
import com.example.tradewolfapp.views.wallets.WalletsScreen

@Composable
fun MainScreen(rootNavController: NavController) {

    val innerNav = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(navController = innerNav)
        }
    ) { innerPadding ->
        NavHost(
            navController = innerNav,
            startDestination = Screens.Home.rout,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screens.Home.rout) { backStackEntry ->
                val authRepository = remember { AuthFirebaseRepository() }
                val loginFactory = remember { LoginWithGoogleViewModelFactory(authRepository) }
                val userViewModel: LoginWithGoogleViewModel = viewModel(
                    viewModelStoreOwner = backStackEntry,
                    factory = loginFactory
                )
                HomeScreen(innerNav, userViewModel)
            }
            composable(Screens.Markets.rout) {
                MarketScreen()
            }
            composable(Screens.Transaction.rout) {
                TransactionScreen()
            }
            composable(Screens.Wallets.rout) {
                WalletsScreen()
            }
            composable(Screens.Profile.rout) {
                ProfileScreen()
            }
        }
    }
}