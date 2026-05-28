package com.ardakazanci.progsettings.ui.theme

import androidx.compose.foundation.style.StyleScope
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography

val StyleScope.colorScheme: ColorScheme
    get() = LocalAppTheme.currentValue.colorScheme

val StyleScope.typography: Typography
    get() = LocalAppTheme.currentValue.typography

val StyleScope.extendedColors: ExtendedColors
    get() = LocalAppTheme.currentValue.extendedColors

val StyleScope.spacing: Spacing
    get() = LocalAppTheme.currentValue.spacing

val StyleScope.shapes: AppShapes
    get() = LocalAppTheme.currentValue.shapes
