package com.ardakazanci.progsettings.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val cardBackground: Color,
    val iconBackground: Color,
    val switchTrackOn: Color,
    val switchTrackOff: Color,
    val switchThumb: Color,
    val switchBorderOff: Color,
    val gaugeTrack: Color,
    val gaugeActive: Color,
    val gaugeOuterGlow: Color,
    val gaugeInnerFill: Color,
    val gaugeThumbFill: Color,
    val gaugeThumbBorder: Color,
    val gaugeTickActive: Color,
    val gaugeTickInactive: Color
)

val LightExtendedColors = ExtendedColors(
    cardBackground = Color(0xFFF7F8FC),
    iconBackground = Color(0xFFE3EDFF),
    switchTrackOn = Palette.Blue600,
    switchTrackOff = Palette.Gray100,
    switchThumb = Palette.White,
    switchBorderOff = Palette.Gray300,
    gaugeTrack = Color(0xFFE4E8F0),
    gaugeActive = Palette.Blue600,
    gaugeOuterGlow = Color(0xFFEEF2FA),
    gaugeInnerFill = Color(0xFFF0F4FF),
    gaugeThumbFill = Palette.White,
    gaugeThumbBorder = Color(0xFFE4E8F0),
    gaugeTickActive = Palette.Blue600.copy(alpha = 0.5f),
    gaugeTickInactive = Color(0xFFD0D5DE)
)

val DarkExtendedColors = ExtendedColors(
    cardBackground = Palette.DarkCard,
    iconBackground = Palette.Blue600.copy(alpha = 0.15f),
    switchTrackOn = Palette.Blue400,
    switchTrackOff = Color(0xFF3A3A50),
    switchThumb = Palette.White,
    switchBorderOff = Color(0xFF4A4A60),
    gaugeTrack = Color(0xFF2E2E42),
    gaugeActive = Palette.Blue400,
    gaugeOuterGlow = Color(0xFF1E1E30),
    gaugeInnerFill = Color(0xFF22223A),
    gaugeThumbFill = Color(0xFF2A2A40),
    gaugeThumbBorder = Color(0xFF3A3A50),
    gaugeTickActive = Palette.Blue400.copy(alpha = 0.5f),
    gaugeTickInactive = Color(0xFF3A3A50)
)
