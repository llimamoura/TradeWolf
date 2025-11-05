package com.example.tradewolfapp.views.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import com.example.tradewolfapp.views.components.OutlinedTextFieldComponent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.views.components.MainButtonComponent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextFieldDefaults
import com.example.tradewolfapp.utils.isFormValid

@Composable
fun Profile(navController: NavController) {
    
    var fullName by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val isFormValid = isFormValid(fullName,cpf,email,phoneNumber)
     
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
            text = "Fill Your Profile",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = BlueLogo,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Don't worry, you can always change it later",
            fontSize = 14.sp,
            color = BlueLogo,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(28.dp))
        
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = BlueLogo, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.person_icon),
                contentDescription = "Profile Image",
                modifier = Modifier.size(90.dp),
                tint = Color.White
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(26.dp)
                    .background(Color(0xFF5E6E7F), CircleShape)
                    .clickable {},
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pen_line),
                    contentDescription = "Edit Profile",
                    tint = Color.White,
                    modifier = Modifier.size(14.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextFieldComponent(
            value = fullName,
            onValueChange = { fullName = it },
            label = "Full name"
        )

        Spacer(modifier = Modifier.height(16.dp))

         OutlinedTextFieldComponent(
            value = cpf,
            onValueChange = { cpf = it },
            label = "CPF"
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldComponent(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

         OutlinedTextFieldComponent(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = "Phone number"
        )

       
        Spacer(modifier = Modifier.height(30.dp))

       
       MainButtonComponent(
            text = "Submit",
            onClick = {  },
            colorText = Color.White,
            colorStart = DeepBlue,
            colorEnd = CobaltBlue,
            isClickable = isFormValid
        )
    }
}