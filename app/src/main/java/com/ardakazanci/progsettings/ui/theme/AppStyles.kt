package com.ardakazanci.progsettings.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.disabled
import androidx.compose.foundation.style.pressed
import androidx.compose.foundation.style.selected
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppStyles {
    val screenContainer: Style = Style {
        background(colorScheme.background)
        contentColor(colorScheme.onBackground)
    }

    val backButton: Style = Style {
        shape(shapes.circle)
        contentColor(colorScheme.onBackground)
        pressed {
            animate {
                scale(0.85f)
                contentColor(colorScheme.onBackground.copy(alpha = 0.6f))
            }
        }
    }

    val topBarTitle: Style = Style {
        contentColor(colorScheme.onBackground)
        textStyle(typography.titleLarge)
    }

    val deviceCard: Style = Style {
        background(extendedColors.cardBackground)
        shape(shapes.card)
        contentPadding(horizontal = spacing.xl, vertical = spacing.lg)
        contentColor(colorScheme.onBackground)
        pressed {
            animate {
                scale(0.98f)
            }
        }
        disabled {
            animate {
                contentColor(colorScheme.onSurfaceVariant)
                alpha(0.58f)
                scale(0.97f)
            }
        }
    }

    val deviceLabel: Style = Style {
        contentColor(colorScheme.onSurfaceVariant)
        textStyle(typography.bodyMedium)
    }

    val deviceName: Style = Style {
        contentColor(colorScheme.onBackground)
        textStyle(typography.titleMedium)
    }

    val usageCard: Style = Style {
        background(extendedColors.cardBackground)
        shape(shapes.card)
        contentPadding(horizontal = spacing.lg, vertical = spacing.lg + spacing.xxs)
        contentColor(colorScheme.onBackground)
        pressed {
            animate {
                scale(0.96f)
            }
        }
    }

    val usageLabel: Style = Style {
        contentColor(colorScheme.onSurfaceVariant)
        textStyle(typography.bodySmall)
    }

    val usageValue: Style = Style {
        contentColor(colorScheme.onBackground)
        textStyle(typography.titleMedium)
    }

    val climateGroup: Style = Style {
        background(extendedColors.cardBackground)
        shape(shapes.cardSmall)
        contentPadding(spacing.xxs)
    }

    val climateOption: Style = Style {
        shape(shapes.chip)
        contentPadding(horizontal = spacing.xs, vertical = spacing.sm)
        contentColor(colorScheme.onSurfaceVariant)
        textStyle(typography.bodySmall)
        selected {
            animate {
                background(colorScheme.primary)
                contentColor(colorScheme.onPrimary)
                fontWeight(FontWeight.SemiBold)
            }
        }
        pressed {
            animate {
                scale(0.96f)
            }
        }
        disabled {
            animate {
                alpha(0.45f)
            }
        }
    }

    val ecoChip: Style = Style {
        background(extendedColors.cardBackground)
        shape(shapes.cardSmall)
        contentPadding(horizontal = spacing.md, vertical = spacing.sm)
        contentColor(colorScheme.onSurfaceVariant)
        textStyle(typography.bodySmall)
        fontWeight(FontWeight.SemiBold)
        selected {
            animate {
                background(colorScheme.primaryContainer)
                contentColor(colorScheme.primary)
            }
        }
        pressed {
            animate {
                scale(0.96f)
            }
        }
        disabled {
            animate {
                alpha(0.45f)
            }
        }
    }

    val gaugeCenterContent: Style = Style {
        shape(CircleShape)
        contentColor(colorScheme.primary)
        pressed {
            animate {
                scale(0.95f)
            }
        }
        disabled {
            animate {
                alpha(0.55f)
            }
        }
    }

    val gaugeTemperature: Style = Style {
        contentColor(colorScheme.primary)
        textStyle(typography.headlineLarge)
        fontWeight(FontWeight.Bold)
        fontSize(46.sp)
    }

    val gaugeSupportingText: Style = Style {
        contentColor(colorScheme.primary)
        textStyle(typography.bodyMedium)
    }

    val gaugeCurrentTemperature: Style = Style {
        contentColor(colorScheme.onBackground)
        textStyle(typography.bodyLarge)
        fontWeight(FontWeight.Medium)
    }

    val gaugeEndpointLabel: Style = Style {
        contentColor(colorScheme.onSurfaceVariant)
        textStyle(typography.bodyMedium)
    }
}
