package com.ardakazanci.progsettings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ardakazanci.progsettings.ui.theme.AppTheme

@Composable
fun UsageCards(
    powerUsage: String,
    timeUsage: String,
    modifier: Modifier = Modifier
) {
    val spacing = AppTheme.spacing
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        UsageCard(
            icon = Icons.Outlined.Bolt,
            label = "Power Usage",
            value = powerUsage,
            modifier = Modifier.weight(1f)
        )
        UsageCard(
            icon = Icons.Outlined.Schedule,
            label = "Time Usage",
            value = timeUsage,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun UsageCard(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    style: Style = Style
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource)
    val ext = AppTheme.extendedColors
    val shapes = AppTheme.shapes
    val spacing = AppTheme.spacing

    Row(
        modifier = modifier
            .styleable(styleState, AppTheme.styles.usageCard, style)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(shapes.circle)
                .background(ext.iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }
        Column {
            Text(
                text = label,
                modifier = Modifier.styleable(style = AppTheme.styles.usageLabel)
            )
            Text(
                text = value,
                modifier = Modifier
                    .padding(top = spacing.xxs)
                    .styleable(style = AppTheme.styles.usageValue)
            )
        }
    }
}
