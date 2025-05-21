package com.example.tradewolfapp.views.auth
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.ui.theme.DarkGray
import com.example.tradewolfapp.viewModel.LoginState
import com.example.tradewolfapp.viewModel.LoginViewModel
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
            is LoginState.Idle -> LoginForm(viewModel::login, onGoogleLogin = {
                user -> onLoginSuccess(user)
            })
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





