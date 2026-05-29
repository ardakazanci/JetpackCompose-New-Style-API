package com.ardakazanci.progsettings.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
    val card: Shape = RoundedCornerShape(16.dp),
    val cardSmall: Shape = RoundedCornerShape(12.dp),
    val button: Shape = RoundedCornerShape(12.dp),
    val circle: Shape = CircleShape,
    val chip: Shape = RoundedCornerShape(8.dp)
)
