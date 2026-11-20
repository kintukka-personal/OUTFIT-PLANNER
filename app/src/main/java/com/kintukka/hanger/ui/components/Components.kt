package com.kintukka.hanger.ui.components

import com.kintukka.hanger.ui.icons.HangerIcons

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ripple
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kintukka.hanger.data.Item
import com.kintukka.hanger.engine.FashionColor
import com.kintukka.hanger.engine.GarmentStatus
import com.kintukka.hanger.engine.WeatherCondition
import com.kintukka.hanger.ui.theme.HangerTokens

/** Garment image on transparent background (or the original photo). */
@Composable
fun ItemImage(item: Item?, modifier: Modifier = Modifier, contentScale: ContentScale = ContentScale.Fit) {
    val file = item?.image
    if (file == null) {
        Box(modifier, contentAlignment = Alignment.Center) {
            val color = item?.entity?.colors?.firstOrNull()
            Box(
                Modifier
                    .fillMaxSize(0.5f)
                    .clip(CircleShape)
                    .background(color?.let { Color(it.rgb or 0xFF000000.toInt()) } ?: MaterialTheme.colorScheme.outlineVariant),
            )
        }
        return
    }
    if (ImageLoading.synchronous) {
        val bitmap = remember(file) { BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap() }
        if (bitmap != null) Image(bitmap, item.name, modifier, contentScale = contentScale)
        return
    }
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(file).crossfade(true).build(),
        contentDescription = item.name,
        contentScale = contentScale,
        modifier = modifier,
    )
}

/** Test hook: decode images inline so screenshots are deterministic. */
object ImageLoading {
    @Volatile var synchronous: Boolean = false
}

/** Square closet tile: warm backdrop, centred garment, status markers, press feedback. */
@Composable
fun ItemTile(
    item: Item,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    showName: Boolean = false,
    badge: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val extras = HangerTokens.extras
    val interaction = remember { MutableInteractionSource() }
    Column(
        modifier
            .then(if (onClick != null) Modifier.pressScale(interaction) else Modifier)
            .clip(MaterialTheme.shapes.medium)
            .then(if (onClick != null) Modifier.clickable(interactionSource = interaction, indication = ripple(), onClick = onClick) else Modifier),
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium)
                .background(extras.tile)
                .then(if (selected) Modifier.border(BorderStroke(2.5.dp, MaterialTheme.colorScheme.primary), MaterialTheme.shapes.medium) else Modifier)
                .semantics { contentDescription = item.name + if (item.status != GarmentStatus.CLEAN) ", ${item.status.label}" else "" },
        ) {
            ItemImage(item, Modifier.fillMaxSize().padding(12.dp))
            if (item.status == GarmentStatus.DIRTY) {
                StatusBubble(Modifier.align(Alignment.TopStart).padding(6.dp)) {
                    Icon(HangerIcons.Laundry, contentDescription = null, tint = extras.laundry, modifier = Modifier.size(14.dp))
                }
            } else if (item.status == GarmentStatus.AWAY) {
                StatusBubble(Modifier.align(Alignment.TopStart).padding(6.dp)) {
                    Text("Away", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(horizontal = 4.dp))
                }
            }
            if (item.entity.favorite) {
                StatusBubble(Modifier.align(Alignment.TopEnd).padding(6.dp)) {
                    Icon(HangerIcons.HeartFilled, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(12.dp))
                }
            }
            if (selected) {
                Box(
                    Modifier.align(Alignment.BottomStart).padding(6.dp).size(22.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center,
                ) { Icon(HangerIcons.Check, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(14.dp)) }
            }
            if (badge != null) {
                Surface(
                    color = MaterialTheme.colorScheme.inverseSurface,
                    shape = CircleShape,
                    modifier = Modifier.align(Alignment.BottomEnd).padding(6.dp),
                ) {
                    Text(
                        badge,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                    )
                }
            }
        }
        if (showName) {
            Text(
                item.name,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 6.dp, start = 4.dp, end = 4.dp, bottom = 2.dp),
            )
        }
    }
}

/** Small frosted bubble for status markers on tiles, so icons stay legible over any garment. */
@Composable
private fun StatusBubble(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.92f),
        shape = CircleShape,
        modifier = modifier,
    ) {
        Box(Modifier.padding(5.dp), contentAlignment = Alignment.Center) { content() }
    }
}

@Composable
fun ColorDot(color: FashionColor, size: Dp = 14.dp, modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(size)
            .clip(CircleShape)
            .background(Color(color.rgb or 0xFF000000.toInt()))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape),
    )
}

@Composable
fun SectionHeader(title: String, modifier: Modifier = Modifier, action: String? = null, onAction: (() -> Unit)? = null) {
    Row(
        modifier.fillMaxWidth().padding(top = 12.dp).heightIn(min = 40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge, modifier = Modifier.weight(1f).semantics { heading() })
        if (action != null && onAction != null) {
            TextButton(onClick = onAction, contentPadding = PaddingValues(horizontal = 12.dp)) {
                Text(action)
                Icon(HangerIcons.Forward, contentDescription = null, modifier = Modifier.padding(start = 4.dp).size(16.dp))
            }
        }
    }
}

/** Big editorial page title used at the top of each tab. */
@Composable
fun PageTitle(title: String, modifier: Modifier = Modifier, subtitle: String? = null, trailing: @Composable (() -> Unit)? = null) {
    Row(modifier.fillMaxWidth().padding(top = 8.dp), verticalAlignment = Alignment.Bottom) {
        Column(Modifier.weight(1f)) {
            if (subtitle != null) {
                Text(
                    subtitle.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(Modifier.height(4.dp))
            }
            Text(title, style = MaterialTheme.typography.displaySmall, modifier = Modifier.semantics { heading() })
        }
        trailing?.invoke()
    }
}

@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box(Modifier.size(104.dp), contentAlignment = Alignment.Center) {
            Box(Modifier.fillMaxSize().clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)))
            Box(
                Modifier.size(76.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(34.dp))
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(title, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
        Text(
            body,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(max = 360.dp),
        )
        if (action != null) {
            Spacer(Modifier.height(8.dp))
            action()
        }
    }
}

@Composable
fun StatPill(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceContainerLowest,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    ) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 14.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(label, style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 0.2.sp), color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2)
        }
    }
}

fun weatherIcon(condition: WeatherCondition?): ImageVector = when (condition) {
    WeatherCondition.CLEAR -> HangerIcons.Sun
    WeatherCondition.PARTLY_CLOUDY -> HangerIcons.PartlyCloudy
    WeatherCondition.CLOUDY -> HangerIcons.Cloud
    WeatherCondition.FOG -> HangerIcons.Fog
    WeatherCondition.DRIZZLE -> HangerIcons.Drizzle
    WeatherCondition.RAIN -> HangerIcons.Rain
    WeatherCondition.SNOW -> HangerIcons.Snow
    WeatherCondition.STORM -> HangerIcons.Storm
    null -> HangerIcons.PartlyCloudy
}

@Composable
fun LabeledValue(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier.fillMaxWidth().padding(vertical = 6.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.width(120.dp))
        Text(value, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
    }
}
