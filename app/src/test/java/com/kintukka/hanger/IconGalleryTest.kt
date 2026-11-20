package com.kintukka.hanger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import com.kintukka.hanger.ui.icons.HangerIcons
import com.kintukka.hanger.ui.theme.HangerTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/** Renders every glyph in the icon set, in both themes, for visual review. */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34], qualifiers = "w600dp-h1400dp-xhdpi")
class IconGalleryTest {
    @get:Rule
    val compose = createComposeRule()

    private val icons = listOf(
        "Today" to HangerIcons.Today,
        "TodayFilled" to HangerIcons.TodayFilled,
        "Hanger" to HangerIcons.Hanger,
        "HangerFilled" to HangerIcons.HangerFilled,
        "Outfit" to HangerIcons.Outfit,
        "OutfitFilled" to HangerIcons.OutfitFilled,
        "Calendar" to HangerIcons.Calendar,
        "CalendarFilled" to HangerIcons.CalendarFilled,
        "CalendarCheck" to HangerIcons.CalendarCheck,
        "Insights" to HangerIcons.Insights,
        "InsightsFilled" to HangerIcons.InsightsFilled,
        "Sparkle" to HangerIcons.Sparkle,
        "Wand" to HangerIcons.Wand,
        "Shuffle" to HangerIcons.Shuffle,
        "Canvas" to HangerIcons.Canvas,
        "Palette" to HangerIcons.Palette,
        "Laundry" to HangerIcons.Laundry,
        "Luggage" to HangerIcons.Luggage,
        "Flame" to HangerIcons.Flame,
        "Bulb" to HangerIcons.Bulb,
        "Camera" to HangerIcons.Camera,
        "Photos" to HangerIcons.Photos,
        "Brush" to HangerIcons.Brush,
        "Scissors" to HangerIcons.Scissors,
        "Sun" to HangerIcons.Sun,
        "PartlyCloudy" to HangerIcons.PartlyCloudy,
        "Cloud" to HangerIcons.Cloud,
        "Fog" to HangerIcons.Fog,
        "Drizzle" to HangerIcons.Drizzle,
        "Rain" to HangerIcons.Rain,
        "Snow" to HangerIcons.Snow,
        "Storm" to HangerIcons.Storm,
        "Thermometer" to HangerIcons.Thermometer,
        "Location" to HangerIcons.Location,
        "MyLocation" to HangerIcons.MyLocation,
        "Back" to HangerIcons.Back,
        "Forward" to HangerIcons.Forward,
        "ChevronLeft" to HangerIcons.ChevronLeft,
        "ChevronRight" to HangerIcons.ChevronRight,
        "ChevronDown" to HangerIcons.ChevronDown,
        "ChevronUp" to HangerIcons.ChevronUp,
        "Close" to HangerIcons.Close,
        "Check" to HangerIcons.Check,
        "Add" to HangerIcons.Add,
        "Remove" to HangerIcons.Remove,
        "Search" to HangerIcons.Search,
        "More" to HangerIcons.More,
        "Sort" to HangerIcons.Sort,
        "Notes" to HangerIcons.Notes,
        "Grid" to HangerIcons.Grid,
        "Edit" to HangerIcons.Edit,
        "Rename" to HangerIcons.Rename,
        "Delete" to HangerIcons.Delete,
        "Refresh" to HangerIcons.Refresh,
        "Undo" to HangerIcons.Undo,
        "Export" to HangerIcons.Export,
        "Restore" to HangerIcons.Restore,
        "Settings" to HangerIcons.Settings,
        "Clock" to HangerIcons.Clock,
        "Keyboard" to HangerIcons.Keyboard,
        "Bell" to HangerIcons.Bell,
        "BellOff" to HangerIcons.BellOff,
        "Lock" to HangerIcons.Lock,
        "LockOpen" to HangerIcons.LockOpen,
        "Price" to HangerIcons.Price,
        "ZoomIn" to HangerIcons.ZoomIn,
        "ZoomOut" to HangerIcons.ZoomOut,
        "BringForward" to HangerIcons.BringForward,
        "SendBackward" to HangerIcons.SendBackward,
        "CheckCircle" to HangerIcons.CheckCircle,
        "Circle" to HangerIcons.Circle,
        "Warning" to HangerIcons.Warning,
        "Heart" to HangerIcons.Heart,
        "HeartFilled" to HangerIcons.HeartFilled,
        "Bookmark" to HangerIcons.Bookmark,
        "BookmarkFilled" to HangerIcons.BookmarkFilled,
        "BookmarkAdd" to HangerIcons.BookmarkAdd,
        "Bookmarks" to HangerIcons.Bookmarks,
        "ThumbUp" to HangerIcons.ThumbUp,
        "ThumbUpFilled" to HangerIcons.ThumbUpFilled,
        "ThumbDown" to HangerIcons.ThumbDown,
        "ThumbDownFilled" to HangerIcons.ThumbDownFilled,    )

    @OptIn(ExperimentalLayoutApi::class)
    private fun render(dark: Boolean, name: String) {
        compose.setContent {
            HangerTheme(darkTheme = dark) {
                FlowRow(
                    Modifier.background(MaterialTheme.colorScheme.background).padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    icons.forEach { (label, icon) ->
                        Column(Modifier.width(66.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Row {
                                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface, modifier = Modifier.size(24.dp))
                                Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(start = 4.dp).size(18.dp))
                            }
                            Text(label, fontSize = 8.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
                        }
                    }
                }
            }
        }
        compose.onRoot().captureRoboImage("screenshots/$name.png")
    }

    /** The launcher icon (circle and squircle masks, plus the themed monochrome version) and the status-bar icon. */
    @Test
    fun appIcon() {
        compose.setContent {
            Row(Modifier.background(androidx.compose.ui.graphics.Color(0xFFECE6E0)).padding(24.dp), horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically) {
                for (shape in listOf(androidx.compose.foundation.shape.CircleShape, androidx.compose.foundation.shape.RoundedCornerShape(32.dp))) {
                    androidx.compose.foundation.layout.Box(Modifier.size(108.dp).then(Modifier).clip(shape)) {
                        androidx.compose.foundation.Image(androidx.compose.ui.res.painterResource(R.drawable.ic_launcher_background), null, Modifier.size(108.dp))
                        androidx.compose.foundation.Image(androidx.compose.ui.res.painterResource(R.drawable.ic_launcher_foreground), null, Modifier.size(108.dp))
                    }
                }
                androidx.compose.foundation.layout.Box(Modifier.size(108.dp).then(Modifier).clip(androidx.compose.foundation.shape.CircleShape).background(androidx.compose.ui.graphics.Color(0xFFDCE5D5))) {
                    androidx.compose.foundation.Image(
                        androidx.compose.ui.res.painterResource(R.drawable.ic_launcher_monochrome), null, Modifier.size(108.dp),
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(androidx.compose.ui.graphics.Color(0xFF2E4A2A)),
                    )
                }
                androidx.compose.foundation.layout.Box(Modifier.background(androidx.compose.ui.graphics.Color(0xFF1C1715)).padding(8.dp)) {
                    androidx.compose.foundation.Image(androidx.compose.ui.res.painterResource(R.drawable.ic_stat_hanger), null, Modifier.size(24.dp))
                }
            }
        }
        compose.onRoot().captureRoboImage("screenshots/icon_launcher.png")
    }

    @Test fun light() = render(false, "icons_light")
    @Test fun dark() = render(true, "icons_dark")
}
