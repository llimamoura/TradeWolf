package com.example.tradewolfapp.views.auth.Login.ResetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.ForgotColor
import com.example.tradewolfapp.views.components.MainButtonComponent

@Composable
fun CheckCode(navController: NavController) {
    val otpLength = 4
    var otpValues = remember { mutableStateOf(List(otpLength) { "" }) }
    var focusRequesters = List(otpLength) { FocusRequester() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp)
            )
        }


        Text(
            text = "Enter verification Code",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Enter code that we have sent to your email /nyour...@domain.com",
            fontSize = 15.sp,
            color = Color.Gray
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            otpValues.value.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            val newList = otpValues.value.toMutableList()
                            newList[index] = newValue
                            otpValues.value = newList


                            if (newValue.isNotEmpty() && index < otpLength - 1) {
                                focusRequesters[index + 1].requestFocus()
                            }
                        }
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp)
                        .focusRequester(focusRequesters[index])
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        MainButtonComponent(
            text = "Submit",
            onClick = {navController.navigate("createpassword") },
            color = BlueLogo,
            colorText = Color.White
        )

        Text(
            text = "Resend code",
            color = ForgotColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable(){

            }
        )
    }
}


