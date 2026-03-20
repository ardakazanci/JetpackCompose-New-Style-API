@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.ardakazanci.progsettings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import com.ardakazanci.progsettings.screen.AirConditionerScreen
import com.ardakazanci.progsettings.ui.theme.ProgSettingsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProgSettingsTheme {
                AirConditionerScreen()
            }
        }
    }
}
