package com.ardakazanci.progsettings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ardakazanci.progsettings.ui.theme.AppTheme

@Composable
fun DeviceCard(
    modifier: Modifier = Modifier,
    style: Style = Style,
    deviceName: String,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource) {
        it.isEnabled = enabled
    }
    val ext = AppTheme.extendedColors
    val spacing = AppTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .styleable(styleState, AppTheme.styles.deviceCard, style)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null
            ) { onToggle(!isOn) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Air Conditioner",
                modifier = Modifier.styleable(style = AppTheme.styles.deviceLabel)
            )
            Text(
                text = deviceName,
                modifier = Modifier
                    .padding(top = spacing.xxs)
                    .styleable(style = AppTheme.styles.deviceName)
            )
        }
        Switch(
            checked = isOn,
            enabled = enabled,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = ext.switchThumb,
                checkedTrackColor = ext.switchTrackOn,
                checkedBorderColor = ext.switchTrackOn,
                uncheckedThumbColor = ext.switchThumb,
                uncheckedTrackColor = ext.switchTrackOff,
                uncheckedBorderColor = ext.switchBorderOff
            )
        )
    }
}
