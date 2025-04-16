package com.example.tradewolfapp.views.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tradewolfapp.R
import com.example.tradewolfapp.model.AuthModel
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.ui.theme.DarkGray
import com.example.tradewolfapp.ui.theme.ForgotColor
import com.example.tradewolfapp.viewModel.LoginState
import com.example.tradewolfapp.viewModel.LoginViewModel
import com.example.tradewolfapp.views.components.IconButtonComponent
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.views.components.TextDivider
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel {
        LoginViewModel(AuthFirebaseRepository())
    },
    onLoginSuccess: (FirebaseUser) -> Unit 
) {
    val loginState by viewModel.loginState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkGray)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center

    ) {
        when (val state = loginState) {
            is LoginState.Idle -> LoginForm(viewModel::login)
            is LoginState.Loading -> {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    CircularProgressIndicator(color = Color.White)
                }
            }
            is LoginState.Success -> {
                onLoginSuccess(state.user)
                Log.d("LoginScreen", state.user.toString())
            }
            is LoginState.Error -> {
                Text(text = state.message, color = Color.Red)
                Button(onClick = { viewModel.resetLoginState() }) {
                    Text("Try again")
                }
            }
            
        }
    }
}



@Composable
fun LoginForm (onLogin: (AuthModel) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    Column {
        Text(
            text = "Sign in with",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Email and Password",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email") },
            textStyle = TextStyle(color = Color.White)
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password") },
            textStyle = TextStyle(color = Color.White)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Forgot Password?",
            color = ForgotColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Right,
        )

        Spacer(modifier = Modifier.height(13.dp))

        MainButtonComponent(
            text = "Sign In",
            onClick = { onLogin(AuthModel(email, password)) },
            color = Color.Gray,
            colorText = Color.White
        )

        Spacer(modifier = Modifier.height(45.dp))

        TextDivider(text = "or continue with")

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButtonComponent(icon = R.drawable.google_logo)
        }

        Spacer(modifier = Modifier.height(65.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,

            ) {
            Text(text = "Don´t have an account? Sign up", color = Color.White)
        }
    }
}

