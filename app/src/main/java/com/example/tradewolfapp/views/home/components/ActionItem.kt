package com.example.tradewolfapp.views.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tradewolfapp.ui.theme.BlueGray

@Composable
fun ActionItem(
    icon: ImageVector,
    description: String,
    text: String,
    onClick: () -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.Blue.copy(0.4f),
            modifier = Modifier.size(28.dp),
            )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text,
            color = Color.Black,
            fontSize = 14.sp,
        )
    }
}


