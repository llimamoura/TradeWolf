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
     
    val textFieldColors = TextFieldDefaults.colors(
            cursorColor = BlueLogo,
            focusedIndicatorColor = BlueLogo,
            unfocusedIndicatorColor = BlueLogo.copy(alpha = 0.5f),
            focusedLabelColor = BlueLogo,
            unfocusedLabelColor = BlueLogo,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 20.dp),
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

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Fill Your Profile",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = BlueLogo,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        
        Text(
            text = "Don't worry, you can always change it later",
            fontSize = 14.sp,
            color = BlueLogo,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(38.dp))
        
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color = BlueLogo, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.circle_user),
                contentDescription = "Profile Image",
                modifier = Modifier.size(100.dp),
                tint = Color.White
            )

            IconButton(
                onClick = {  },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(32.dp)
                    .background(Color(0xFF5E6E7F), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pen_line),
                    contentDescription = "Edit Profile",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 16.sp, color = BlueLogo),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 16.sp, color = BlueLogo),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 16.sp, color = BlueLogo),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontSize = 16.sp, color = BlueLogo),
            shape = RoundedCornerShape(12.dp),
            colors = textFieldColors
        )

       
        Spacer(modifier = Modifier.height(40.dp))

       
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