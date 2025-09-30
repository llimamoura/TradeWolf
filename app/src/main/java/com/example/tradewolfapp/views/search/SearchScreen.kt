package com.example.tradewolfapp.views.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.tradewolfapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradewolfapp.ui.theme.DeepBlue
import com.example.tradewolfapp.ui.theme.DullGray
import com.example.tradewolfapp.ui.theme.CobaltBlue
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle


@Composable
fun SearchScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient( 
                    colorStops = arrayOf(
                        0.0f to Color.White,
                        0.5f to Color.White,
                        1.0f to DullGray
                    ))
            )
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
         Row(
            modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 0.dp, vertical = 50.dp),
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
            )}
        }

        Text(
            text = "Search for currencies and \nand services",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                brush = Brush.verticalGradient( 
                    colors = listOf(DeepBlue, CobaltBlue)
                )
            )
        )

    }
}

