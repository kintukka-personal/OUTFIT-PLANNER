package com.kintukka.hanger.ui.components

import com.kintukka.hanger.ui.icons.HangerIcons

import androidx.compose.ui.layout.layout
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRowScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Hanger's design system on top of Material 3.
 *
 * - Selection is always "ink": selected chips and segments use the inverse surface (near-black on paper,
 *   paper on night), so the rose accent stays reserved for actions and the active tab.
 * - Cards are white on warm paper with a hairline border, no drop shadows.
 * - Pressable tiles scale down slightly on press (transform only, no layout shift).
 */
object Hanger {
    /** Horizontal page gutter. */
    val Gutter: Dp = 20.dp

    /** Content never stretches wider than this on tablets, foldables and landscape. */
    val MaxContentWidth: Dp = 840.dp

    /** Standard heights for primary buttons and touch targets. */
    val ButtonHeight: Dp = 52.dp
    val TouchTarget: Dp = 48.dp
}

/** Window size class, provided once by the navigation shell. */
enum class WindowWidth { Compact, Medium, Expanded }

val LocalWindowWidth = compositionLocalOf { WindowWidth.Compact }

fun windowWidthFor(width: Dp): WindowWidth = when {
    width >= 840.dp -> WindowWidth.Expanded
    width >= 600.dp -> WindowWidth.Medium
    else -> WindowWidth.Compact
}

/** Filter chip in Hanger's style: ink when selected, a check that animates in, 36dp tall with a 48dp touch area. */
@Composable
fun HangerFilterChip(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
) {
    val cs = MaterialTheme.colorScheme
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = label,
        modifier = modifier,
        enabled = enabled,
        leadingIcon = leadingIcon ?: if (selected) {
            { Icon(HangerIcons.Check, contentDescription = null, modifier = Modifier.size(FilterChipDefaults.IconSize)) }
        } else null,
        trailingIcon = trailingIcon,
        shape = CircleShape,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = cs.surfaceContainerLowest,
            labelColor = cs.onSurface,
            iconColor = cs.onSurfaceVariant,
            selectedContainerColor = cs.inverseSurface,
            selectedLabelColor = cs.inverseOnSurface,
            selectedLeadingIconColor = cs.inverseOnSurface,
            selectedTrailingIconColor = cs.inverseOnSurface,
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled,
            selected = selected,
            borderColor = cs.outlineVariant,
            selectedBorderColor = cs.inverseSurface,
            borderWidth = 1.dp,
            selectedBorderWidth = 1.dp,
        ),
    )
}

/** Segmented button with ink selection, matching [HangerFilterChip]. */
@Composable
fun SingleChoiceSegmentedButtonRowScope.HangerSegmentedButton(
    selected: Boolean,
    onClick: () -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: @Composable () -> Unit = { SegmentedCheck(selected) },
    label: @Composable () -> Unit,
) {
    val cs = MaterialTheme.colorScheme
    SegmentedButton(
        selected = selected,
        onClick = onClick,
        shape = shape,
        modifier = modifier,
        enabled = enabled,
        icon = icon,
        label = label,
        colors = SegmentedButtonDefaults.colors(
            activeContainerColor = cs.inverseSurface,
            activeContentColor = cs.inverseOnSurface,
            activeBorderColor = cs.inverseSurface,
            inactiveContainerColor = cs.surfaceContainerLowest,
            inactiveContentColor = cs.onSurface,
            inactiveBorderColor = cs.outlineVariant,
        ),
    )
}

/** A horizontally scrolling row of chips that bleeds to the screen edge but lines up with the page gutter. */
@Composable
fun ChipRow(modifier: Modifier = Modifier, gutter: Dp = Hanger.Gutter, content: LazyListScope.() -> Unit) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = gutter),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        content = content,
    )
}

/** White card on paper with a hairline border. Clickable when [onClick] is set, with press feedback. */
@Composable
fun HangerCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.large,
    content: @Composable () -> Unit,
) {
    val colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
    val border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    if (onClick != null) {
        val interaction = remember { MutableInteractionSource() }
        Card(onClick = onClick, modifier = modifier.pressScale(interaction), shape = shape, colors = colors, border = border, interactionSource = interaction) { content() }
    } else {
        Card(modifier = modifier, shape = shape, colors = colors, border = border) { content() }
    }
}

/** Subtle press feedback: scales the element down to 97% while pressed (graphics layer only, no relayout). */
fun Modifier.pressScale(interaction: MutableInteractionSource, pressed: Float = 0.97f): Modifier = composed {
    val isPressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(if (isPressed) pressed else 1f, spring(stiffness = 600f), label = "press")
    graphicsLayer { scaleX = scale; scaleY = scale }
}

/** Row of equal-weight children, e.g. stat pills; keeps their heights equal. */
@Composable
fun EqualRow(modifier: Modifier = Modifier, spacing: Dp = 10.dp, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(spacing),
        content = content,
    )
}

/** Small uppercase overline used above titles. */
@Composable
fun Overline(text: String, modifier: Modifier = Modifier, color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurfaceVariant) {
    Text(text.uppercase(), style = MaterialTheme.typography.labelMedium, color = color, modifier = modifier)
}

/**
 * Lets a horizontally scrolling row inside a padded container run to the screen edges.
 * Pair with `contentPadding = PaddingValues(horizontal = gutter)` so the first item still lines up with the page.
 */
fun Modifier.bleedHorizontally(gutter: Dp = Hanger.Gutter): Modifier = layout { measurable, constraints ->
    val extra = gutter.roundToPx() * 2
    val placeable = measurable.measure(
        constraints.copy(
            minWidth = (constraints.minWidth + extra).coerceAtLeast(0),
            maxWidth = if (constraints.hasBoundedWidth) constraints.maxWidth + extra else constraints.maxWidth,
        ),
    )
    val width = if (constraints.hasBoundedWidth) constraints.maxWidth else placeable.width
    layout(width, placeable.height) { placeable.place(-gutter.roundToPx(), 0) }
}

/** The segmented-button check from Hanger's icon set, scaling in when selected (replaces Material's built-in glyph). */
@Composable
private fun SegmentedCheck(selected: Boolean) {
    androidx.compose.animation.AnimatedVisibility(
        visible = selected,
        enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.scaleIn(),
        exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.scaleOut(),
    ) {
        Icon(HangerIcons.Check, contentDescription = null, modifier = Modifier.size(SegmentedButtonDefaults.IconSize))
    }
}

/** Dropdown arrow from Hanger's icon set that flips when the menu is open. */
@Composable
fun DropdownArrow(expanded: Boolean) {
    val rotation by animateFloatAsState(if (expanded) 180f else 0f, label = "dropdown-arrow")
    Icon(
        HangerIcons.ChevronDown,
        contentDescription = null,
        modifier = Modifier.size(20.dp).graphicsLayer { rotationZ = rotation },
    )
}
