@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.ardakazanci.progsettings.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.pressed
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeviceThermostat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import com.ardakazanci.progsettings.ui.theme.AppTheme

private const val START_ANGLE = 135f
private const val SWEEP_ANGLE = 270f
private const val TICK_COUNT = 60

@Immutable
data class GaugeColors(
    val activeArc: Color,
    val trackArc: Color,
    val outerGlow: Color,
    val innerFill: Color,
    val thumbFill: Color,
    val thumbBorder: Color,
    val thumbShadow: Color,
    val tickActive: Color,
    val tickInactive: Color,
    val iconTint: Color,
    val temperatureText: Color,
    val labelText: Color
)

@Immutable
data class GaugeDimensions(
    val strokeWidth: Dp = 12.dp,
    val thumbRadius: Dp = 12.dp,
    val thumbShadowRadius: Dp = 14.dp,
    val outerGlowExtra: Dp = 28.dp,
    val innerFillInset: Dp = 4.dp,
    val tickOuterOffset: Dp = 12.dp,
    val majorTickLength: Dp = 5.dp,
    val minorTickLength: Dp = 3.dp,
    val majorTickWidth: Dp = 1.5.dp,
    val minorTickWidth: Dp = 0.8.dp
)

object TemperatureGaugeDefaults {

    @Composable
    fun colors(
        activeArc: Color = AppTheme.extendedColors.gaugeActive,
        trackArc: Color = AppTheme.extendedColors.gaugeTrack,
        outerGlow: Color = AppTheme.extendedColors.gaugeOuterGlow,
        innerFill: Color = AppTheme.extendedColors.gaugeInnerFill,
        thumbFill: Color = AppTheme.extendedColors.gaugeThumbFill,
        thumbBorder: Color = AppTheme.extendedColors.gaugeThumbBorder,
        thumbShadow: Color = Color.Black.copy(alpha = 0.1f),
        tickActive: Color = AppTheme.extendedColors.gaugeTickActive,
        tickInactive: Color = AppTheme.extendedColors.gaugeTickInactive,
        iconTint: Color = MaterialTheme.colorScheme.primary,
        temperatureText: Color = MaterialTheme.colorScheme.onBackground,
        labelText: Color = MaterialTheme.colorScheme.onSurfaceVariant
    ): GaugeColors = GaugeColors(
        activeArc = activeArc,
        trackArc = trackArc,
        outerGlow = outerGlow,
        innerFill = innerFill,
        thumbFill = thumbFill,
        thumbBorder = thumbBorder,
        thumbShadow = thumbShadow,
        tickActive = tickActive,
        tickInactive = tickInactive,
        iconTint = iconTint,
        temperatureText = temperatureText,
        labelText = labelText
    )

    fun dimensions(): GaugeDimensions = GaugeDimensions()

    @Composable
    fun centerContentStyle(): Style {
        val primary = MaterialTheme.colorScheme.primary
        return Style {
            shape(CircleShape)
            contentColor(primary)
            pressed(Style {
                animate(Style {
                    scale(0.95f)
                })
            })
        }
    }
}

@Composable
fun TemperatureGauge(
    currentTemperature: Float,
    minTemp: Float,
    maxTemp: Float,
    progress: Float,
    onProgressChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    colors: GaugeColors = TemperatureGaugeDefaults.colors(),
    dimensions: GaugeDimensions = TemperatureGaugeDefaults.dimensions(),
    centerStyle: Style = TemperatureGaugeDefaults.centerContentStyle()
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(150),
        label = "gaugeProgress"
    )

    val displayTemp = remember(animatedProgress, minTemp, maxTemp) {
        val temp = minTemp + animatedProgress * (maxTemp - minTemp)
        temp.toInt()
    }

    val centerInteractionSource = remember { MutableInteractionSource() }
    val centerStyleState = remember { MutableStyleState(centerInteractionSource) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        change.consume()
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val touchAngle = atan2(
                            change.position.y - center.y,
                            change.position.x - center.x
                        ) * (180f / PI.toFloat())

                        val normalizedAngle = ((touchAngle - START_ANGLE + 360f) % 360f)
                        if (normalizedAngle in 0f..SWEEP_ANGLE) {
                            val newProgress = (normalizedAngle / SWEEP_ANGLE).coerceIn(0f, 1f)
                            onProgressChange(newProgress)
                        }
                    }
                }
        ) {
            val gaugeRadius = size.minDimension / 2f * 0.75f
            val center = Offset(size.width / 2f, size.height / 2f)
            val stroke = dimensions.strokeWidth.toPx()

            drawCircle(
                color = colors.outerGlow,
                radius = gaugeRadius + dimensions.outerGlowExtra.toPx(),
                center = center
            )

            drawCircle(
                color = colors.innerFill,
                radius = gaugeRadius - stroke / 2f - dimensions.innerFillInset.toPx(),
                center = center
            )

            drawArc(
                color = colors.trackArc,
                startAngle = START_ANGLE,
                sweepAngle = SWEEP_ANGLE,
                useCenter = false,
                style = Stroke(width = stroke, cap = StrokeCap.Round),
                topLeft = Offset(center.x - gaugeRadius, center.y - gaugeRadius),
                size = Size(gaugeRadius * 2, gaugeRadius * 2)
            )

            if (animatedProgress > 0.001f) {
                drawArc(
                    color = colors.activeArc,
                    startAngle = START_ANGLE,
                    sweepAngle = SWEEP_ANGLE * animatedProgress,
                    useCenter = false,
                    style = Stroke(width = stroke, cap = StrokeCap.Round),
                    topLeft = Offset(center.x - gaugeRadius, center.y - gaugeRadius),
                    size = Size(gaugeRadius * 2, gaugeRadius * 2)
                )
            }

            drawTickMarks(center, gaugeRadius, animatedProgress, colors, dimensions)

            val thumbAngle = START_ANGLE + SWEEP_ANGLE * animatedProgress
            val thumbRad = thumbAngle * (PI.toFloat() / 180f)
            val thumbPos = Offset(
                center.x + gaugeRadius * cos(thumbRad),
                center.y + gaugeRadius * sin(thumbRad)
            )

            drawCircle(
                color = colors.thumbShadow,
                radius = dimensions.thumbShadowRadius.toPx(),
                center = thumbPos + Offset(0f, 2.dp.toPx())
            )
            drawCircle(
                color = colors.thumbFill,
                radius = dimensions.thumbRadius.toPx(),
                center = thumbPos
            )
            drawCircle(
                color = colors.thumbBorder,
                radius = dimensions.thumbRadius.toPx(),
                center = thumbPos,
                style = Stroke(width = 1.5.dp.toPx())
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.styleable(
                styleState = centerStyleState,
                style = centerStyle
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.DeviceThermostat,
                contentDescription = null,
                tint = colors.iconTint,
                modifier = Modifier.size(28.dp)
            )
            Text(
                text = "${displayTemp}°C",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 46.sp
                ),
                color = colors.temperatureText
            )
            Text(
                text = "Room temperature",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.labelText
            )
        }

        Text(
            text = "${minTemp.toInt()}°",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.labelText,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 24.dp, bottom = 36.dp)
        )

        Text(
            text = "${maxTemp.toInt()}°",
            style = MaterialTheme.typography.bodyMedium,
            color = colors.labelText,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 36.dp)
        )

        Text(
            text = "${currentTemperature.toInt()}°",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
            color = colors.temperatureText,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
        )
    }
}

private fun DrawScope.drawTickMarks(
    center: Offset,
    radius: Float,
    progress: Float,
    colors: GaugeColors,
    dimensions: GaugeDimensions
) {
    val tickRadius = radius + dimensions.tickOuterOffset.toPx()
    val majorLen = dimensions.majorTickLength.toPx()
    val minorLen = dimensions.minorTickLength.toPx()
    val majorW = dimensions.majorTickWidth.toPx()
    val minorW = dimensions.minorTickWidth.toPx()

    for (i in 0 until TICK_COUNT) {
        val tickProgress = i.toFloat() / TICK_COUNT
        val angle = START_ANGLE + SWEEP_ANGLE * tickProgress
        val rad = angle * (PI.toFloat() / 180f)

        val isMajor = i % 5 == 0
        val len = if (isMajor) majorLen else minorLen
        val w = if (isMajor) majorW else minorW

        val inner = Offset(
            center.x + tickRadius * cos(rad),
            center.y + tickRadius * sin(rad)
        )
        val outer = Offset(
            center.x + (tickRadius + len) * cos(rad),
            center.y + (tickRadius + len) * sin(rad)
        )

        drawLine(
            color = if (tickProgress <= progress) colors.tickActive else colors.tickInactive,
            start = inner,
            end = outer,
            strokeWidth = w,
            cap = StrokeCap.Round
        )
    }
}
