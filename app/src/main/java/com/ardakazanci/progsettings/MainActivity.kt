package com.ardakazanci.progsettings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ComposeFoundationFlags
import androidx.compose.foundation.ExperimentalFoundationApi
import com.ardakazanci.progsettings.screen.AirConditionerScreen
import com.ardakazanci.progsettings.ui.theme.ProgSettingsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        ComposeFoundationFlags.isInheritedTextStyleEnabled = true
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProgSettingsTheme {
                AirConditionerScreen()
            }
        }
    }
}
