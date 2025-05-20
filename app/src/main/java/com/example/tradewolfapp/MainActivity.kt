package com.example.tradewolfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tradewolfapp.ui.theme.TradeWolfAppTheme
import com.example.tradewolfapp.viewModel.CoinsViewModel
import com.example.tradewolfapp.views.HomeScreen
import com.example.tradewolfapp.views.auth.LoginScreen
import com.example.tradewolfapp.views.WelcomeScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)

        installSplashScreen()
        enableEdgeToEdge()
        lifecycle.addObserver(AuthLifecycleObserver())
        setContent {
            TradeWolfAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "welcomeScreen"
                ) {
                    composable("welcomeScreen") {
                        WelcomeScreen(navController)
                    }
                    composable("loginScreen") {
                        LoginScreen(navController, onLoginSuccess = { user ->
                                navController.navigate("home") {
                                    popUpTo("home"){ inclusive = true}
                                }
                        })
                    }
                    composable("home") { backStackEntry ->
                        val viewModel: CoinsViewModel = viewModel(backStackEntry)
                       HomeScreen(navController, viewModel)
                    }

                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) return
    }
}

class AuthLifecycleObserver : DefaultLifecycleObserver {
    override fun onDestroy(owner: LifecycleOwner) {
        if ((owner as ComponentActivity).isFinishing) {
            Firebase.auth.signOut()
        }
    }
}