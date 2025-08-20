
package com.example.tradewolfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tradewolfapp.ui.theme.TradeWolfAppTheme
import com.example.tradewolfapp.views.auth.Login.LoginScreen
import com.example.tradewolfapp.views.WelcomeScreen
import com.example.tradewolfapp.views.auth.Login.ResetPassword.RecoverPassword
import com.example.tradewolfapp.views.navigations.MainScreen
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
                        LoginScreen(navController, onLoginSuccess = { _ ->
                            navController.navigate("main") {
                                popUpTo("main"){ inclusive = true}
                            }
                        })
                    }
                    composable("recoverPassword"){
                        RecoverPassword(navController)
                    }

                    composable("main") {
                        MainScreen(navController)
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
