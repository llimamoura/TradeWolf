package com.example.tradewolfapp.views.auth.Login.ResetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.style.TextAlign
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.views.components.MainButtonComponent

@Composable
fun CreatePassword(navController : NavController) {
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
   

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
         horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
            text = "Create password",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Create your new password to login",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextFieldComponent(
           value = password,
           onValueChange = {password = it},
           label = "Password",
           isPassword = true
        )
        
        Spacer(modifier = Modifier.height(26.dp))
        
        OutlinedTextFieldComponent(
           value = password,
           onValueChange  = {password = it},
           label = "Password",
           isPassword = true
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        MainButtonComponent(
            text = "Submit",
            onClick = {},
            color = BlueLogo,
            colorText = Color.White
        )
    }
}

