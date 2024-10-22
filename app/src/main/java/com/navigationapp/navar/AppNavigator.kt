package com.navigationapp.navar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppNavigator() {
    // System UI Controller for changing status bar color
    val systemUiController = rememberSystemUiController()

    // State to control which screen is displayed
    val showSplashScreen = remember { mutableStateOf(true) }
    val showMainScreen = remember { mutableStateOf(false) }
    val showQrScanner = remember { mutableStateOf(false) }

    // Handle other buttons' state
    val showMoreScreen = remember { mutableStateOf(false) }
    val showSearchScreen = remember { mutableStateOf(false) }
    val showAboutScreen = remember { mutableStateOf(false) }

    // Splash Screen
    if (showSplashScreen.value) {
        // Set the status bar color for SplashScreen
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

        SplashScreen {
            showSplashScreen.value = false // Close splash screen
            showMainScreen.value = true // Show main screen
        }

        // Main Screen
    } else if (showMainScreen.value) {
        // Set the status bar color for MainScreen
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )

        MainScreen(
            onNavigateToScanner = {
                showQrScanner.value = true // Navigate to QR Scanner
                showMainScreen.value = false // Hide MainScreen
            },
            onMoreClicked = {
                showMoreScreen.value = true // Handle more button click
                showMainScreen.value = false // Hide MainScreen
            },
            onSearchClicked = {
                showSearchScreen.value = true // Handle search button click
                showMainScreen.value = false // Hide MainScreen
            },
            onAboutClicked = {
                showAboutScreen.value = true // Handle about button click
                showMainScreen.value = false // Hide MainScreen
            }
        )

        // QR Scanner Screen
    } else if (showQrScanner.value) {
        // Set the status bar color for QR Scanner Screen
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )

        QrScannerScreen { scannedCode ->
            showQrScanner.value = false // Close the scanner
            showMainScreen.value = true // Navigate back to MainScreen
            // Handle scanned code if needed
        }

        // More Screen (handle this as per your requirements)
    } else if (showMoreScreen.value) {
        // Set the status bar color for MoreScreen
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )

        MoreScreen {
            showMoreScreen.value = false // Close the More screen
            showMainScreen.value = true // Navigate back to MainScreen
        }

        // Search Screen (handle this as per your requirements)
    } else if (showSearchScreen.value) {
        // Set the status bar color for SearchScreen
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )

        SearchScreen {
            showSearchScreen.value = false // Close the Search screen
            showMainScreen.value = true // Navigate back to MainScreen
        }

        // About Screen (handle this as per your requirements)
    } else if (showAboutScreen.value) {
        // Set the status bar color for AboutScreen
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = true
        )

        AboutScreen {
            showAboutScreen.value = false // Close the About screen
            showMainScreen.value = true // Navigate back to MainScreen
        }
    }
}

