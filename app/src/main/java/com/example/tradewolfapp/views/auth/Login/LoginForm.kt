package com.example.tradewolfapp.views.auth.Login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tradewolfapp.model.AuthModel
import com.example.tradewolfapp.repository.AuthFirebaseRepository
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.ForgotColor
import com.example.tradewolfapp.viewModel.auth.LoginWithGoogleViewModel
import com.example.tradewolfapp.viewModel.auth.LoginWithGoogleViewModelFactory
import com.example.tradewolfapp.views.components.GoogleSignInButtonComponent
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent
import com.example.tradewolfapp.views.components.TextDivider
import com.google.firebase.auth.FirebaseUser
import androidx.compose.foundation.layout.padding
@Composable
fun LoginForm(
    onLogin: (AuthModel) -> Unit,
    onGoogleLogin: (FirebaseUser) -> Unit,
    authRepository: AuthFirebaseRepository = AuthFirebaseRepository(),
    loginWithGoogleViewModel: LoginWithGoogleViewModel = viewModel(
        factory = LoginWithGoogleViewModelFactory(authRepository)
    ),
    navController: NavController
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
                user?.let { onGoogleLogin(it) }
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
        
        IconButton(
            onClick = { navController.popBackStack() }
            
        ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
            )
        }

            
        Text(
            text = "Let´s log you in",
             fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextFieldComponent(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextFieldComponent(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
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
            text = "Sing in",
            onClick = { onLogin(AuthModel(email, password)) },
            color = BlueLogo,
            colorText = Color.White
        )

        Spacer(modifier = Modifier.height(25.dp))

        TextDivider(text = "or continue with")

        Spacer(modifier = Modifier.height(25.dp))

        GoogleSignInButtonComponent(
            onClick = {
                loginWithGoogleViewModel.loginWithGoogle(context)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don’t have an account? ", color = Color.Black)
            Text(
                text = "Sign up",
                color = BlueLogo,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable {}
            )
        }
    }
}
