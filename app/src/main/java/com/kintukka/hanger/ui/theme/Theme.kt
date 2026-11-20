package com.kintukka.hanger.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kintukka.hanger.R

// Palette: warm paper + ink with a fashion rose accent and a gold highlight.
// Light surfaces step from paper (page) to white (cards) so cards read without heavy borders or shadows.
private val Paper = Color(0xFFF8F4F0)
private val Ink = Color(0xFF1C1715)
private val Rose = Color(0xFFB0124F)
private val RoseContainer = Color(0xFFFBE4EC)
private val OnRoseContainer = Color(0xFF52082A)
private val Gold = Color(0xFF9A4A07)
private val GoldContainer = Color(0xFFF9EBD6)
private val Muted = Color(0xFF655B55)
private val Line = Color(0xFFE6DCD4)
private val Tile = Color(0xFFEFE8E1)

private val NightPaper = Color(0xFF131011)
private val NightSurface = Color(0xFF1C1819)
private val NightTile = Color(0xFF282224)
private val NightInk = Color(0xFFF5EFEB)
private val NightRose = Color(0xFFF58BB5)
private val NightMuted = Color(0xFFBDB2AC)

private val LightColors = lightColorScheme(
    primary = Rose,
    onPrimary = Color.White,
    primaryContainer = RoseContainer,
    onPrimaryContainer = OnRoseContainer,
    secondary = Gold,
    onSecondary = Color.White,
    secondaryContainer = GoldContainer,
    onSecondaryContainer = Color(0xFF431F04),
    tertiary = Color(0xFF356552),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFDAEDE3),
    onTertiaryContainer = Color(0xFF0E2C21),
    background = Paper,
    onBackground = Ink,
    surface = Paper,
    onSurface = Ink,
    surfaceVariant = Tile,
    onSurfaceVariant = Muted,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF5F0EB),
    surfaceContainer = Color(0xFFF1EBE5),
    surfaceContainerHigh = Color(0xFFEBE4DD),
    surfaceContainerHighest = Color(0xFFE5DDD5),
    outline = Color(0xFFA89C93),
    outlineVariant = Line,
    error = Color(0xFFB3261E),
    inverseSurface = Ink,
    inverseOnSurface = Paper,
    inversePrimary = NightRose,
    scrim = Color(0xFF1C1715),
)

private val DarkColors = darkColorScheme(
    primary = NightRose,
    onPrimary = Color(0xFF3D0820),
    primaryContainer = Color(0xFF5A1737),
    onPrimaryContainer = Color(0xFFFFD9E6),
    secondary = Color(0xFFF2B35C),
    onSecondary = Color(0xFF2E1A00),
    secondaryContainer = Color(0xFF45310F),
    onSecondaryContainer = Color(0xFFFFE2B3),
    tertiary = Color(0xFF8CC7AE),
    onTertiary = Color(0xFF07291C),
    tertiaryContainer = Color(0xFF1F3A30),
    onTertiaryContainer = Color(0xFFCDEBDD),
    background = NightPaper,
    onBackground = NightInk,
    surface = NightPaper,
    onSurface = NightInk,
    surfaceVariant = NightTile,
    onSurfaceVariant = NightMuted,
    surfaceContainerLowest = Color(0xFF1E1A1B),
    surfaceContainerLow = NightSurface,
    surfaceContainer = Color(0xFF221D1F),
    surfaceContainerHigh = Color(0xFF2B2527),
    surfaceContainerHighest = Color(0xFF352E31),
    outline = Color(0xFF857973),
    outlineVariant = Color(0xFF3A3234),
    inverseSurface = NightInk,
    inverseOnSurface = NightPaper,
    inversePrimary = Rose,
)

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold),
)

val Serif = FontFamily(
    Font(R.font.instrument_serif_regular, FontWeight.Normal),
    Font(R.font.instrument_serif_italic, FontWeight.Normal, FontStyle.Italic),
)

private val base = Typography()

val HangerTypography = Typography(
    displayLarge = TextStyle(fontFamily = Serif, fontSize = 54.sp, lineHeight = 58.sp, letterSpacing = (-0.6).sp),
    displayMedium = TextStyle(fontFamily = Serif, fontSize = 44.sp, lineHeight = 48.sp, letterSpacing = (-0.4).sp),
    displaySmall = TextStyle(fontFamily = Serif, fontSize = 38.sp, lineHeight = 42.sp, letterSpacing = (-0.3).sp),
    headlineLarge = TextStyle(fontFamily = Serif, fontSize = 32.sp, lineHeight = 36.sp, letterSpacing = (-0.2).sp),
    headlineMedium = TextStyle(fontFamily = Serif, fontSize = 28.sp, lineHeight = 32.sp, letterSpacing = (-0.1).sp),
    headlineSmall = TextStyle(fontFamily = Serif, fontSize = 24.sp, lineHeight = 28.sp),
    titleLarge = base.titleLarge.copy(fontFamily = Inter, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, lineHeight = 26.sp, letterSpacing = (-0.2).sp),
    titleMedium = base.titleMedium.copy(fontFamily = Inter, fontWeight = FontWeight.SemiBold, letterSpacing = (-0.1).sp),
    titleSmall = base.titleSmall.copy(fontFamily = Inter, fontWeight = FontWeight.SemiBold, letterSpacing = 0.sp),
    bodyLarge = base.bodyLarge.copy(fontFamily = Inter, letterSpacing = 0.1.sp),
    bodyMedium = base.bodyMedium.copy(fontFamily = Inter, lineHeight = 21.sp, letterSpacing = 0.1.sp),
    bodySmall = base.bodySmall.copy(fontFamily = Inter, lineHeight = 18.sp, letterSpacing = 0.15.sp),
    labelLarge = base.labelLarge.copy(fontFamily = Inter, fontWeight = FontWeight.SemiBold, letterSpacing = 0.1.sp),
    labelMedium = base.labelMedium.copy(fontFamily = Inter, fontWeight = FontWeight.SemiBold, letterSpacing = 0.6.sp),
    labelSmall = base.labelSmall.copy(fontFamily = Inter, fontWeight = FontWeight.Medium, letterSpacing = 0.4.sp),
)

val HangerShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(30.dp),
)

/** Extra tokens Material doesn't model. */
@Immutable
data class HangerExtras(
    val tile: Color,
    val success: Color,
    val warning: Color,
    val laundry: Color,
)

val LocalExtras = staticCompositionLocalOf {
    HangerExtras(tile = Tile, success = Color(0xFF2F7D5B), warning = Color(0xFFB45309), laundry = Color(0xFF2563EB))
}

@Composable
fun HangerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors: ColorScheme = if (darkTheme) DarkColors else LightColors
    val extras = if (darkTheme) {
        HangerExtras(tile = NightTile, success = Color(0xFF6FCF97), warning = Color(0xFFF5B041), laundry = Color(0xFF7FB2FF))
    } else {
        HangerExtras(tile = Tile, success = Color(0xFF2F7D5B), warning = Color(0xFFB45309), laundry = Color(0xFF2563EB))
    }
    CompositionLocalProvider(LocalExtras provides extras) {
        MaterialTheme(colorScheme = colors, typography = HangerTypography, shapes = HangerShapes, content = content)
    }
}

object HangerTokens {
    val extras: HangerExtras
        @Composable get() = LocalExtras.current
}
