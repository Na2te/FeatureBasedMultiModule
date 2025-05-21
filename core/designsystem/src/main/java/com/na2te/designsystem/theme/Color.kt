package com.na2te.designsystem.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ProjectColors(
    val primaryText: Color,
    val secondaryText: Color,
    val background: Color,
    val chartLegend: Color
)

val LightColorScheme = ProjectColors(
    primaryText = Color(0xFF82ACCF),
    secondaryText = Color(0xFFB5E7E5),
    background = Color(0xFFA5F7CB),
    chartLegend = Color(0xFFD2FC90)
)

val LocalColorScheme = staticCompositionLocalOf { LightColorScheme }

/**
 * 메테리얼 Color Scheme를 안쓰기 위해 전부 미설정
 */
val materialColorScheme = lightColorScheme(
    primary = Color.Unspecified,
    onPrimary = Color.Unspecified,
    primaryContainer = Color.Unspecified,
    onPrimaryContainer = Color.Unspecified,
    inversePrimary = Color.Unspecified,
    secondary = Color.Unspecified,
    onSecondary = Color.Unspecified,
    secondaryContainer = Color.Unspecified,
    onSecondaryContainer = Color.Unspecified,
    tertiary = Color.Unspecified,
    onTertiary = Color.Unspecified,
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,
    background = Color.Unspecified,
    onBackground = Color.Unspecified,
    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    surfaceVariant = Color.Unspecified,
    onSurfaceVariant = Color.Unspecified,
    surfaceTint = Color.Unspecified,
    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,
    error = Color.Unspecified,
    onError = Color.Unspecified,
    errorContainer = Color.Unspecified,
    onErrorContainer = Color.Unspecified,
    outline = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    scrim = Color.Unspecified,
    surfaceBright = Color.Unspecified,
    surfaceContainer = Color.Unspecified,
    surfaceContainerHigh = Color.Unspecified,
    surfaceContainerHighest = Color.Unspecified,
    surfaceContainerLow = Color.Unspecified,
    surfaceContainerLowest = Color.Unspecified,
    surfaceDim = Color.Unspecified,
)
