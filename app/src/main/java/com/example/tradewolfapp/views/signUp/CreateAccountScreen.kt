package com.example.tradewolfapp.views.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.viewModel.auth.SignUpState
import com.example.tradewolfapp.viewModel.auth.SignUpViewModel
import com.google.firebase.auth.FirebaseUser

@Composable
fun CreateAccountScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel(),
    onSignUpSuccess: (FirebaseUser) -> Unit
) {
    val signUpState by viewModel.signUpState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        when (val state = signUpState) {

            is SignUpState.Idle ->
                CreateAccount(
                    onSignUp = viewModel::signUp,
                    navController = navController
                )

            is SignUpState.Loading ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color.Black)
                }

            is SignUpState.Success -> {
                onSignUpSuccess(state.user)
            }

            is SignUpState.Error -> {
                Text(text = state.message, color = Color.Red)
                Button(onClick = { viewModel.resetSignUpState()}) {
                    Text("Try again")
                }
            }
        }
    }
}
