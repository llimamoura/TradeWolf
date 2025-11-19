package com.example.tradewolfapp.views.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.views.components.MainButtonComponent
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent


@Composable
fun CreateAccount(
    navController : NavController
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    val passwordFocusRequester = remember { FocusRequester() }
    val repeatPasswordFocusRequester = remember { FocusRequester() }

    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
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
            text = "Create Account",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center,
            modifier  = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(17.dp))

        Text(
            text = "Enter your email and create your password",
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(69.dp))

        OutlinedTextFieldComponent(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            )
        )

        Spacer(modifier = Modifier.height(38.dp))

        OutlinedTextFieldComponent(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true,
            modifier = Modifier.focusRequester(passwordFocusRequester),
            keyboardOptions = KeyboardOptions.Default.copy( imeAction = ImeAction.Next ),
            keyboardActions = KeyboardActions(
                onNext = { repeatPasswordFocusRequester.requestFocus() }
            )
        )

        Spacer(modifier = Modifier.height(38.dp))

        OutlinedTextFieldComponent(
            value = repeatPassword,
            onValueChange = { repeatPassword = it },
            label = "Repeat Password",
            isPassword = true,
            modifier = Modifier.focusRequester(repeatPasswordFocusRequester)
        )

        Spacer(modifier = Modifier.height(66.dp))

        MainButtonComponent(
            text = "Create",
            onClick = {},
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue
        )
    }
}

