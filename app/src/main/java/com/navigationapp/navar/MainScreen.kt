package com.navigationapp.navar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    onNavigateToScanner: () -> Unit,
    onMoreClicked: () -> Unit,
    onSearchClicked: () -> Unit,
    onAboutClicked: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.main_screen_bg_img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Bottom Bar (Scan from here Button)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(Color(0xFFFFA500))
                .padding(16.dp)
        ) {
            Text(
                text = "Scan from here",
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable { onNavigateToScanner() },
                color = Color.White,
                fontSize = 18.sp,
                style = MaterialTheme.typography.h6
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search Button
            IconButton(
                onClick = onSearchClicked,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFFFA500), shape = RoundedCornerShape(16.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = onAboutClicked,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFFFA500), shape = RoundedCornerShape(16.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.ThumbUp,
                    contentDescription = "About",
                    tint = Color.White
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 100.dp)
        ) {
            IconButton(
                onClick = onMoreClicked,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFFFA500), shape = RoundedCornerShape(16.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "More",
                    tint = Color.White
                )
            }
        }
    }
}
