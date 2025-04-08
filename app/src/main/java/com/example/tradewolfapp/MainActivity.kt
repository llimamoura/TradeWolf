package com.example.tradewolfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tradewolfapp.ui.theme.TradeWolfAppTheme
import com.example.tradewolfapp.views.LoginScreen
import com.example.tradewolfapp.views.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)

        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "welcomeScreen"
            ) {
                composable("welcomeScreen") {
                    WelcomeScreen(navController)
                }
                composable("loginScreen") {
                    LoginScreen(navController)
                }
            }
            TradeWolfAppTheme {
            }
        }
    }
}