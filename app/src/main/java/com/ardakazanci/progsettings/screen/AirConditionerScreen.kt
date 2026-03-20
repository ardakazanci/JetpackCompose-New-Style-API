@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.ardakazanci.progsettings.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ardakazanci.progsettings.components.AcTopBar
import com.ardakazanci.progsettings.components.DeviceCard
import com.ardakazanci.progsettings.components.TemperatureGauge
import com.ardakazanci.progsettings.components.UsageCards
import com.ardakazanci.progsettings.ui.theme.AppTheme

object AirConditionerScreenDefaults {
    @Composable
    fun containerStyle(): Style {
        val bg = MaterialTheme.colorScheme.background
        val onBg = MaterialTheme.colorScheme.onBackground
        return Style {
            background(bg)
            contentColor(onBg)
        }
    }
}

@Composable
fun AirConditionerScreen(
    viewModel: AirConditionerViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    style: Style = AirConditionerScreenDefaults.containerStyle()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val spacing = AppTheme.spacing

    Column(
        modifier = Modifier
            .fillMaxSize()
            .styleable(style = style)
            .statusBarsPadding()
            .padding(horizontal = spacing.xl)
    ) {
        AcTopBar(
            title = "Air Conditioner",
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(spacing.lg))

        DeviceCard(
            deviceName = state.deviceName,
            isOn = state.isOn,
            onToggle = { viewModel.togglePower() }
        )

        Spacer(modifier = Modifier.height(spacing.md))

        UsageCards(
            powerUsage = "${state.powerUsageKwh} kwh",
            timeUsage = state.formattedTimeUsage
        )

        Spacer(modifier = Modifier.height(spacing.xxl))

        TemperatureGauge(
            currentTemperature = state.currentTemperature,
            minTemp = state.minTemperature,
            maxTemp = state.maxTemperature,
            progress = state.temperatureProgress,
            onProgressChange = { progress ->
                val temp = state.minTemperature + progress * (state.maxTemperature - state.minTemperature)
                viewModel.updateTargetTemperature(temp)
            }
        )
    }
}
