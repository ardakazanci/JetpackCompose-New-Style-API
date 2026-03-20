@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.ardakazanci.progsettings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.disabled
import androidx.compose.foundation.style.pressed
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ardakazanci.progsettings.ui.theme.AppTheme

object DeviceCardDefaults {
    @Composable
    fun style(): Style {
        val ext = AppTheme.extendedColors
        val shapes = AppTheme.shapes
        val spacing = AppTheme.spacing
        val onBg = MaterialTheme.colorScheme.onBackground
        val subColor = MaterialTheme.colorScheme.onSurfaceVariant
        return Style {
            background(ext.cardBackground)
            shape(shapes.card)
            contentPadding(horizontal = spacing.xl, vertical = spacing.lg)
            contentColor(onBg)
            pressed(Style {
                animate(Style {
                    scale(0.98f)
                })
            })
            disabled(Style {
                contentColor(subColor)
                scale(0.97f)
            })
        }
    }
}

@Composable
fun DeviceCard(
    deviceName: String,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    style: Style = DeviceCardDefaults.style()
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = remember { MutableStyleState(interactionSource) }
    val ext = AppTheme.extendedColors
    val spacing = AppTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .styleable(styleState = styleState, style = style)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onToggle(!isOn) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Air Conditioner",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = deviceName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(top = spacing.xxs)
            )
        }
        Switch(
            checked = isOn,
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
