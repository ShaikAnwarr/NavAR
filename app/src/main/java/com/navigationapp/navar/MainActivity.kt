// MainActivity.kt
package com.navigationapp.navar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.navigationapp.navar.ui.theme.NavARTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavARTheme {
                AppNavigator()
            }
        }
    }
}
