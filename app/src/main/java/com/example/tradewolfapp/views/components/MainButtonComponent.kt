package com.example.tradewolfapp.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tradewolfapp.utils.isFormValid
import com.example.tradewolfapp.ui.theme.DarkGray
import com.example.tradewolfapp.ui.theme.DullGray
import androidx.compose.foundation.clickable


@Composable
fun MainButtonComponent(
    text: String,
    onClick: () -> Unit,
    colorStart: Color,
    colorEnd: Color,
    colorText: Color,
    isClickable: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                brush = if(isClickable) Brush.horizontalGradient(colors = listOf(colorStart, colorEnd)) else Brush.horizontalGradient(colors = listOf(DullGray,DarkGray)),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                onClick = onClick,
                enabled = isClickable
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent 
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
          Text(
            text = text,
            color = colorText
            )      
        }
        
    }
}
