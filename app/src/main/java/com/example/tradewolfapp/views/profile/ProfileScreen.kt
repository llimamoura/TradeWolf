package com.example.tradewolfapp.views.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.tradewolfapp.ui.theme.BlueLogo
import com.example.tradewolfapp.views.components.MainButtonComponent
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.R
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.CobaltBlue
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun ProfileScreen() {
    var fullName by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF00224F), Color(0xFF004EB5))
                )
            )
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(horizontal = 22.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "Profile",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = BlueLogo,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(25.dp))

            
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = {Text("Full name")},
                placeholder = { Text("Nome") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = {Text("CPF")},
                placeholder = { Text("000.000.000-00") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {Text("Email")},
                placeholder = { Text("...@gmail.com") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = {Text("Phone number")},
                placeholder = { Text("+0 (000)000-000") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))

            MainButtonComponent(
                text = "Log out",
                onClick = { },
                colorText = Color.White,
                colorStart = DeepBlue,
                colorEnd = CobaltBlue
            )
        }

        
        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.TopCenter)
                .offset(y = 95.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.circle_user),
                contentDescription = "Profile Image",
                modifier = Modifier
                .size(100.dp)
                .background(color = BlueLogo, CircleShape),
                tint = Color.White
            )

            
            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(14.dp)
                    .background(Color(0xFF5E6E7F), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pen_line),
                    contentDescription = "Edit Profile",
                    tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
            }
        }
    }
}
