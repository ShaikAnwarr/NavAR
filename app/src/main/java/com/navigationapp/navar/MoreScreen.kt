package com.navigationapp.navar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.navigationapp.navar.R

@Composable
fun MoreScreen(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set the background color to white
    ) {

        // Foreground content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            // Top Bar with Back Icon and Menu Text
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 8.dp)
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Menu", style = MaterialTheme.typography.h6)
            }

            // User Information Card (Now Larger)
            Card(
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color(0xFFFFA500),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(160.dp) // Increased height to make it vertically bigger
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Hello!",
                        fontSize = 28.sp, // Bigger text size for "Hello!"
                        color = Color.White,
                        fontWeight = FontWeight.Bold // Bold "Hello!"
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_user_profile),
                            contentDescription = "User",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(text = "Shaik Anwar", fontSize = 18.sp, color = Color.White)
                            Text(text = "9117072647", fontSize = 14.sp, color = Color.Gray)
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "Rating",
                            modifier = Modifier
                                .size(20.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "4.00 / 5.00 ", fontSize = 20.sp, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Menu Items as Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // 2 columns grid
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Add space between columns
                verticalArrangement = Arrangement.spacedBy(8.dp) // Add space between rows
            ) {
                items(menuItems.size) { index ->
                    MenuItemSquare(menuItem = menuItems[index])
                }
            }
        }
    }
}

@Composable
fun MenuItemSquare(menuItem: MenuItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color(0xFFFFA500),
        modifier = Modifier
            .size(120.dp) // Square size for the menu item
            .clickable { /* Handle menu item click */ }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = menuItem.iconRes),
                    contentDescription = menuItem.title,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = menuItem.title,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

// Data class for MenuItem
data class MenuItem(val title: String, val iconRes: Int)

// Example of Menu Items List
val menuItems = listOf(
    MenuItem("Help", R.drawable.help),
    MenuItem("Settings", R.drawable.settings),
    MenuItem("Safety", R.drawable.help),
    MenuItem("Refer and Earn", R.drawable.settings),
    MenuItem("My Rewards", R.drawable.settings),
    MenuItem("Logout", R.drawable.claims),
    MenuItem("FAQs", R.drawable.settings),
    MenuItem("Terms & Conditions", R.drawable.settings),
    MenuItem("Privacy Policy", R.drawable.settings),
)
