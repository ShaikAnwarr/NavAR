package com.navigationapp.navar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.navigationapp.navar.R

@Composable
fun SearchScreen(
    onBack: () -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    // Background Image
    val backgroundImage: Painter = painterResource(id = R.drawable.main_screen_bg_img) // Your background image

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Fallback background color
    ) {
        // Apply the background image to cover the whole screen
        Image(
            painter = backgroundImage,
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Content Overlay with Search Bar and Back Button
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top Section (Back Arrow and Search Box)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp)) // Apply rounded corners
                    .background(Color(0xFFFFA500)) // Background color for the top section
                    .padding(8.dp)
            ) {
                // Back Button with Rounded Corners Box
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp)) // Rounded square with 12dp corners
                        .background(Color(0xFFFFA500))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Search Box with Icon
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp)) // Apply rounded corners to search box
                        .background(Color(0xFFFFA500))
                        .padding(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Search Icon
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // Search TextField
                        BasicTextField(
                            value = searchQuery,
                            onValueChange = { newQuery -> searchQuery = newQuery },
                            modifier = Modifier
                                .weight(1f),
                            singleLine = true,
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    if (searchQuery.text.isEmpty()) {
                                        Text("Where are you going?", color = Color.Gray, fontSize = 16.sp)
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
