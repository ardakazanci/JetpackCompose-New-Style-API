package com.ardakazanci.progsettings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Eco
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ardakazanci.progsettings.model.ClimateMode
import com.ardakazanci.progsettings.model.FanSpeed
import com.ardakazanci.progsettings.ui.theme.AppTheme

@Composable
fun ClimateControls(
    mode: ClimateMode,
    fanSpeed: FanSpeed,
    isEcoMode: Boolean,
    onModeChange: (ClimateMode) -> Unit,
    onFanSpeedChange: (FanSpeed) -> Unit,
    onEcoToggle: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val spacing = AppTheme.spacing

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.sm)
    ) {
        ClimateModeSelector(
            selectedMode = mode,
            onModeChange = onModeChange,
            enabled = enabled
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FanSpeedSelector(
                selectedFanSpeed = fanSpeed,
                onFanSpeedChange = onFanSpeedChange,
                enabled = enabled,
                modifier = Modifier.weight(1f)
            )
            EcoChip(
                selected = isEcoMode,
                onClick = onEcoToggle,
                enabled = enabled
            )
        }
    }
}

@Composable
private fun ClimateModeSelector(
    selectedMode: ClimateMode,
    onModeChange: (ClimateMode) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    SegmentedGroup(modifier = modifier) {
        ClimateMode.entries.forEach { mode ->
            ClimateOption(
                text = mode.label,
                selected = mode == selectedMode,
                enabled = enabled,
                onClick = { onModeChange(mode) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun FanSpeedSelector(
    selectedFanSpeed: FanSpeed,
    onFanSpeedChange: (FanSpeed) -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    SegmentedGroup(modifier = modifier) {
        FanSpeed.entries.forEach { fanSpeed ->
            ClimateOption(
                text = fanSpeed.label,
                selected = fanSpeed == selectedFanSpeed,
                enabled = enabled,
                onClick = { onFanSpeedChange(fanSpeed) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SegmentedGroup(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val styleState = rememberUpdatedStyleState(null)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .styleable(styleState, AppTheme.styles.climateGroup),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

@Composable
private fun ClimateOption(
    text: String,
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource) {
        it.isSelected = selected
        it.isEnabled = enabled
    }

    Row(
        modifier = modifier
            .styleable(styleState, AppTheme.styles.climateOption)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.RadioButton,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun EcoChip(
    selected: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource) {
        it.isSelected = selected
        it.isEnabled = enabled
    }
    val color = if (selected) {
        AppTheme.colorScheme.primary
    } else {
        AppTheme.colorScheme.onSurfaceVariant
    }

    Row(
        modifier = modifier
            .styleable(styleState, AppTheme.styles.ecoChip)
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                role = Role.Switch,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.xs),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Eco,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(17.dp)
        )
        Text(
            text = "Eco",
            maxLines = 1
        )
    }
}
