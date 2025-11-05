package com.example.tradewolfapp.views.auth.Login.ResetPassword

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction


@Composable
fun CreatePassword(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    val passwordsMatch = password == repeatPassword
    val passwordFocusRequest  = remember { FocusRequester() }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        

         Row(
            modifier = Modifier.fillMaxWidth()
            .padding(vertical = 50.dp),
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
        
        Text(
            text = "Create password",
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Create your new password to login",
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextFieldComponent(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequest.requestFocus() }
            )
        )

        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextFieldComponent(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = "Repeat Password",
            isPassword = true,
            modifier =  Modifier.focusRequester(passwordFocusRequest)
        )

        if (repeatPassword.isNotEmpty() && !passwordsMatch) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Passwords do not match",
                color = Color.Red,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(66.dp))

        MainButtonComponent(
            text = "Submit",
            onClick = {},
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue
        )
    }
}
