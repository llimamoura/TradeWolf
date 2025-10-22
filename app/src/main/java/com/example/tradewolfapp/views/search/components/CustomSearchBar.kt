package com.example.tradewolfapp.views.search.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import com.example.tradewolfapp.ui.theme.CobaltBlue
import com.example.tradewolfapp.ui.theme.DeepBlue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Brush


@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    active: Boolean,
    onActiveChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    Column(modifier = modifier) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 2.dp,
                        color = DeepBlue,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    BasicTextField(
                        value = query,
                        onValueChange = { newQuery ->
                            onQueryChange(newQuery)
                            onActiveChange(newQuery.isNotBlank())
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(
                            onSearch = { onSearch() }
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (query.isEmpty()) {
                                    placeholder()
                                }
                                innerTextField()
                            }
                        }
                    )

                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .background(
                                brush = Brush.verticalGradient(colors = listOf(DeepBlue,CobaltBlue)),
                                shape = CircleShape
                            )
                            .clickable { onSearch() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Search, // icone temporario, 
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        if (active) {
            content()
        }
    }

    
    LaunchedEffect(active) {
        if (active) {
            focusRequester.requestFocus()
        }
    }
}