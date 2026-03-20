package com.ardakazanci.progsettings.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Palette.Blue600,
    onPrimary = Palette.White,
    primaryContainer = Palette.Blue50,
    onPrimaryContainer = Palette.Gray900,
    secondary = Palette.Blue400,
    background = Palette.White,
    onBackground = Palette.Gray900,
    surface = Palette.White,
    onSurface = Palette.Gray900,
    surfaceVariant = Palette.Gray50,
    onSurfaceVariant = Palette.Gray500,
    outline = Palette.Gray300,
    outlineVariant = Palette.Gray100
)

private val DarkColorScheme = darkColorScheme(
    primary = Palette.Blue400,
    onPrimary = Palette.Black,
    primaryContainer = Palette.Blue600.copy(alpha = 0.2f),
    onPrimaryContainer = Palette.White,
    secondary = Palette.Blue600,
    background = Palette.DarkBackground,
    onBackground = Palette.White,
    surface = Palette.DarkSurface,
    onSurface = Palette.White,
    surfaceVariant = Palette.DarkCard,
    onSurfaceVariant = Palette.DarkGray,
    outline = Color(0xFF4A4A60),
    outlineVariant = Color(0xFF3A3A50)
)

object AppTheme {
    val extendedColors: ExtendedColors
        @Composable @ReadOnlyComposable
        get() = LocalExtendedColors.current

    val spacing: Spacing
        @Composable @ReadOnlyComposable
        get() = LocalSpacing.current

    val shapes: AppShapes
        @Composable @ReadOnlyComposable
        get() = LocalAppShapes.current
}

@Composable
fun ProgSettingsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalExtendedColors provides extendedColors,
        LocalSpacing provides Spacing(),
        LocalAppShapes provides AppShapes()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
