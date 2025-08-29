package com.example.tradewolfapp.views.auth.Login.ResetPassword

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent

@Composable
fun RecoverPassword(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        
        Row(
            modifier = Modifier.fillMaxWidth()
            .padding(start = 0.dp , top = 34.dp),
            verticalAlignment = Alignment.CenterVertically,
            
        ) {
             Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = BlueLogo,
                    modifier = Modifier
                    .size(24.dp)
                    .clickable {navController.popBackStack()}
                )
            
        }

        Spacer(modifier = Modifier.height(40.dp))

     
        Text(
            text = "Forgot your password?",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

     
        Text(
            text = "No worries, you just need to type your email address we will send the verification code.",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
            
        )

        Spacer(modifier = Modifier.height(36.dp))

        
        OutlinedTextFieldComponent(
           value = email,
           onValueChange  = {email= it},
           label = "Email"
        )
        

        Spacer(modifier = Modifier.height(42.dp))

        
        MainButtonComponent(
            text = "Sign in",
            onClick = { navController.navigate("checkcode") },
            color = BlueLogo,
            colorText = Color.White
        )
    }
}
