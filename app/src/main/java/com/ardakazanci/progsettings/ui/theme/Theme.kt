package com.ardakazanci.progsettings.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
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

@Immutable
data class AppThemeTokens(
    val colorScheme: androidx.compose.material3.ColorScheme,
    val typography: androidx.compose.material3.Typography,
    val extendedColors: ExtendedColors,
    val spacing: Spacing,
    val shapes: AppShapes
)

val LocalAppTheme = staticCompositionLocalOf {
    AppThemeTokens(
        colorScheme = LightColorScheme,
        typography = Typography,
        extendedColors = LightExtendedColors,
        spacing = Spacing(),
        shapes = AppShapes()
    )
}

object AppTheme {
    val colorScheme: ColorScheme
        @Composable @ReadOnlyComposable
        get() = LocalAppTheme.current.colorScheme

    val typography: androidx.compose.material3.Typography
        @Composable @ReadOnlyComposable
        get() = LocalAppTheme.current.typography

    val styles: AppStyles
        get() = AppStyles

    val extendedColors: ExtendedColors
        @Composable @ReadOnlyComposable
        get() = LocalAppTheme.current.extendedColors

    val spacing: Spacing
        @Composable @ReadOnlyComposable
        get() = LocalAppTheme.current.spacing

    val shapes: AppShapes
        @Composable @ReadOnlyComposable
        get() = LocalAppTheme.current.shapes
}

@Composable
fun ProgSettingsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors
    val appTheme = remember(colorScheme, extendedColors) {
        AppThemeTokens(
            colorScheme = colorScheme,
            typography = Typography,
            extendedColors = extendedColors,
            spacing = Spacing(),
            shapes = AppShapes()
        )
    }

    CompositionLocalProvider(
        LocalAppTheme provides appTheme
    ) {
        MaterialTheme(
            colorScheme = appTheme.colorScheme,
            typography = appTheme.typography,
            content = content
        )
    }
}
