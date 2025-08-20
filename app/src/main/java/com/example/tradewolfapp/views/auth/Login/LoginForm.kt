package com.example.tradewolfapp.views.auth.Login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tradewolfapp.R
import com.example.tradewolfapp.model.AuthModel
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.ForgotColor
import com.example.tradewolfapp.viewModel.auth.LoginWithGoogleViewModel
import com.example.tradewolfapp.viewModel.auth.LoginWithGoogleViewModelFactory
import com.example.tradewolfapp.views.components.IconButtonComponent
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.views.components.TextDivider
import com.google.firebase.auth.FirebaseUser

@Composable
fun LoginForm(
    onLogin: (AuthModel) -> Unit,
    onGoogleLogin: (FirebaseUser) -> Unit,
    authRepository: AuthFirebaseRepository = AuthFirebaseRepository(),
    loginWithGoogleViewModel: LoginWithGoogleViewModel = viewModel(
        factory = LoginWithGoogleViewModelFactory(authRepository)
    )
    ,navController : NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberUser by remember { mutableStateOf(false) }
    val loginState by loginWithGoogleViewModel.loginState.collectAsState()
    val user by loginWithGoogleViewModel.user.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(loginState) {
        when (loginState) {
            is LoginWithGoogleViewModel.LoginResult.Success -> {
                user?.let {
                    onGoogleLogin(it)
                }
                Log.e("User photo", user?.photoUrl.toString())
        }
            is LoginWithGoogleViewModel.LoginResult.Failure -> {
                val errorMessage =
                    (loginState as LoginWithGoogleViewModel.LoginResult.Failure).message
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
            else -> {}

        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Sign in with ",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Email and password",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(30.dp))


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") },
            textStyle = TextStyle(color = Color.Black),
            shape = androidx.compose.material3.MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(15.dp))


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .height(60.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            textStyle = TextStyle(color = Color.Black),
            shape = androidx.compose.material3.MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(20.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberUser,
                    onCheckedChange = { rememberUser = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Blue.copy(alpha = 0.6f)
                    )
                )
                Text(
                    text = "Remember me",
                    color = Color.Black,
                    fontSize = 15.sp
                )
            }

            Text(
                text = "Reset password",
                color = ForgotColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {
                    navController.navigate("recoverPassword")
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))


        MainButtonComponent(
            text = "SIGN IN",
            onClick = { onLogin(AuthModel(email, password)) },
            color = BlueLogo,
            colorText = Color.White,
        )

        Spacer(modifier = Modifier.height(25.dp))


        TextDivider(text = "or continue with")

        Spacer(modifier = Modifier.height(25.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButtonComponent(icon = R.drawable.facebook_logo, onClick = {

            }, contentDescription = "Login with Facebook")
            IconButtonComponent(icon = R.drawable.google_logo, onClick = {
                loginWithGoogleViewModel.loginWithGoogle(context)
            }, contentDescription = "Login with Google")
            IconButtonComponent(icon = R.drawable.apple_logo, onClick = {

           }, contentDescription = "Login with Apple")
        }

        Spacer(modifier = Modifier.height(30.dp))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don’t have an account? ", color = Color.Black)
            Text(
                text = "Sign up",
                color = ForgotColor,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {

                }
            )
        }
    }
}
