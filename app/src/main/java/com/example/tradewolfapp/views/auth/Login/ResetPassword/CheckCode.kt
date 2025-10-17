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
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.ui.theme.ForgotColor
import com.example.tradewolfapp.views.components.MainButtonComponent
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.R
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle





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
            text = "Verify Code",
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = buildAnnotatedString {
                append("Enter code that we have sent to your email\nyour...")
                withStyle(style =  SpanStyle(color = BlueLogo, fontWeight = FontWeight.Medium)){ 
                    append("@domain.com")
                }
            },
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(38.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            otpValues.forEachIndexed { index, value ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.LightGray.copy(alpha = 0.6f), shape = RoundedCornerShape(20.dp)),
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
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(34.dp))
        
        Text(
            text = "Resend code",
            color = BlueLogo,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.height(34.dp))

        MainButtonComponent(
            text = "Submit",
            onClick = { navController.navigate("createpassword") },
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue
        )
    }
}
