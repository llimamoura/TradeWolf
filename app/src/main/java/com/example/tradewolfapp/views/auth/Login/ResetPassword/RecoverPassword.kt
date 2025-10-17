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
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.views.components.MainButtonComponent
import androidx.compose.foundation.background
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.R


@Composable
fun RecoverPassword(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
            .padding( vertical = 50.dp),
            verticalAlignment = Alignment.CenterVertically,
            
        ) {
            Box(
                modifier  = Modifier
                .size(48.dp)
                .background(
                    color = Color.Gray.copy(alpha = 0.09f),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { navController.popBackStack() },
                contentAlignment  = Alignment.Center
            ){
                Image( 
                painter  = painterResource(id = R.drawable.back_icon),
                contentDescription  = "Back",
                modifier = Modifier
                .size(38.dp)
            )
            }
        }
        

        Spacer(modifier = Modifier.height(40.dp))

     
        Text(
            text = "Forgot your password?",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

     
        Text(
            text = "No worries, you just need to type your email address we will send the verification code.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
            
        )

        Spacer(modifier = Modifier.height(37.dp))

        
        OutlinedTextFieldComponent(
           value = email,
           onValueChange  = {email= it},
           label = "Email"
        )
        

        Spacer(modifier = Modifier.height(66.dp))

        
        MainButtonComponent(
            text = "Sing in",
            onClick = {navController.navigate("checkcode") },
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue
        )
    }
}
