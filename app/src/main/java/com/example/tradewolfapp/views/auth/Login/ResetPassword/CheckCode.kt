package com.example.tradewolfapp.views.auth.Login.ResetPassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.ForgotColor
import com.example.tradewolfapp.views.components.MainButtonComponent

@Composable
fun CheckCode(navController: NavController) {
    val otpLength = 4
    val otpValues = remember { mutableStateListOf(*Array(otpLength) { "" }) }
    val focusRequesters = remember { List(otpLength) { FocusRequester() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 34.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = BlueLogo,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Verify Code",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Enter code that we have sent to your email \nyour...@domain.com",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            otpValues.forEachIndexed { index, value ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    OutlinedTextField(
                        value = value,
                        onValueChange = { newValue ->
                            val currentValue = otpValues[index]
                            if (newValue.length <= 1 && (newValue.isEmpty() || newValue.all { it.isDigit() })) {
                                otpValues[index] = newValue
                                when {
                                    newValue.isNotEmpty() && index < otpValues.lastIndex ->
                                        focusRequesters[index + 1].requestFocus()
                                    newValue.isEmpty() && currentValue.isEmpty() && index > 0 ->
                                        focusRequesters[index - 1].requestFocus()
                                    newValue.isEmpty() && currentValue.isNotEmpty() && index > 0 -> {
                                        focusRequesters[index - 1].requestFocus()
                                    }
                                }
                            }
                        },
                        singleLine = true,
                        textStyle = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxSize()
                            .focusRequester(focusRequesters[index]),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Resend code",
            color = ForgotColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.height(20.dp))

        MainButtonComponent(
            text = "Submit",
            onClick = { navController.navigate("createpassword") },
            color = BlueLogo,
            colorText = Color.White
        )
    }
}
