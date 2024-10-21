// AppNavigator.kt
package com.navigationapp.navar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun AppNavigator() {
    // State to control which screen is displayed
    val showSplashScreen = remember { mutableStateOf(true) }
    val showMainScreen = remember { mutableStateOf(false) }
    val showQrScanner = remember { mutableStateOf(false) }

    // Navigation logic
    if (showSplashScreen.value) {
        SplashScreen {
            showSplashScreen.value = false // Close splash screen
            showMainScreen.value = true // Show main screen
        }
    } else if (showMainScreen.value) {
        MainScreen(onNavigateToScanner = {
            showQrScanner.value = true // Navigate to QR Scanner
            showMainScreen.value = false // Hide MainScreen
        })
    } else if (showQrScanner.value) {
        QrScannerScreen { scannedCode ->
            showQrScanner.value = false // Close the scanner
            showMainScreen.value = true // Navigate back to MainScreen
            // Handle scanned code if needed
        }
    }
}
