package com.kintukka.hanger.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.unit.dp

/**
 * Hanger's own icon set.
 *
 * Every glyph is drawn on a 24dp grid with a 1.75 stroke and round caps and joins, so icons share one
 * visual weight and sit comfortably next to Inter. Filled variants exist only for states (selected tab,
 * favourite, saved, liked); everything else is a line icon. Icons are tinted by `Icon(tint = …)` like
 * any Material icon. Directional icons (back, forward, undo, list notes) mirror in right-to-left layouts.
 */
object HangerIcons {
    // ---- Navigation (outline + filled for the selected tab) ----
    val Today by lazy {
        icon("Today") {
            circle(12f, 12f, 4f)
            stroke("M12 2.5v2 M12 19.5v2 M2.5 12h2 M19.5 12h2 M5.3 5.3l1.4 1.4 M17.3 17.3l1.4 1.4 M5.3 18.7l1.4-1.4 M17.3 6.7l1.4-1.4")
        }
    }
    val TodayFilled by lazy {
        icon("TodayFilled") {
            fill(circlePath(12f, 12f, 5f))
            stroke("M12 2.5v2 M12 19.5v2 M2.5 12h2 M19.5 12h2 M5.3 5.3l1.4 1.4 M17.3 17.3l1.4 1.4 M5.3 18.7l1.4-1.4 M17.3 6.7l1.4-1.4", width = 2f)
        }
    }

    /** A clothes hanger: the closet. */
    val Hanger by lazy { icon("Hanger") { stroke(HANGER_HOOK); stroke(HANGER_BODY) } }
    val HangerFilled by lazy { icon("HangerFilled") { stroke(HANGER_HOOK, width = 2f); fill(HANGER_BODY); stroke(HANGER_BODY, width = 2f) } }

    /** A tee: outfits. */
    val Outfit by lazy { icon("Outfit") { stroke(TEE) } }
    val OutfitFilled by lazy { icon("OutfitFilled") { fill(TEE); stroke(TEE) } }

    val Calendar by lazy { icon("Calendar") { stroke(CALENDAR); dots(8f to 14f, 12f to 14f, 16f to 14f, 8f to 17.5f, 12f to 17.5f) } }
    val CalendarFilled by lazy {
        icon("CalendarFilled") {
            stroke(CALENDAR)
            fill("M5.5 4.5h13a2 2 0 0 1 2 2V10h-17V6.5a2 2 0 0 1 2-2Z")
            dots(8f to 14f, 12f to 14f, 16f to 14f, 8f to 17.5f, 12f to 17.5f)
        }
    }
    val CalendarCheck by lazy { icon("CalendarCheck") { stroke(CALENDAR); stroke("M9 15.2l2 2 4-4.2") } }

    val Insights by lazy { icon("Insights") { stroke(BARS); stroke("M3 20.5h18") } }
    val InsightsFilled by lazy { icon("InsightsFilled") { fill(BARS); stroke(BARS); stroke("M3 20.5h18") } }

    // ---- Brand actions ----
    /** Four-point sparkle: the stylist, suggestions, "style it". */
    val Sparkle by lazy {
        icon("Sparkle") {
            stroke("M11 4l1.6 4.9a2 2 0 0 0 1.3 1.3L18.8 11.8l-4.9 1.6a2 2 0 0 0-1.3 1.3L11 19.6l-1.6-4.9a2 2 0 0 0-1.3-1.3L3.2 11.8l4.9-1.6a2 2 0 0 0 1.3-1.3Z")
            stroke("M19 2.5v4 M17 4.5h4 M18.5 17.5v3 M17 19h3")
        }
    }
    /** Wand: auto-arrange / tidy layout. */
    val Wand by lazy { icon("Wand") { stroke("M4 20 14.5 9.5 M12.5 7.5l4 4"); stroke("M18 2.5v4 M16 4.5h4 M8 3v2.5 M6.75 4.25h2.5 M20 10.5v2.5 M18.75 11.75h2.5") } }
    val Shuffle by lazy {
        icon("Shuffle") {
            stroke("M3 7h3c2.2 0 3.6 1.1 4.8 2.9l2.4 3.6C14.4 15.3 15.8 16.5 18 16.5h3 M18 4l3 3-3 3 M18 13.5l3 3-3 3")
            stroke("M3 16.5h3c1.4 0 2.4-.5 3.2-1.3 M14.8 8.3C15.6 7.5 16.6 7 18 7h3")
        }
    }
    /** Layout panels: the outfit canvas. */
    val Canvas by lazy {
        icon("Canvas") {
            stroke(rr(3.5f, 3.5f, 7.5f, 9.5f)); stroke(rr(13f, 3.5f, 7.5f, 5.5f))
            stroke(rr(13f, 11f, 7.5f, 9.5f)); stroke(rr(3.5f, 15f, 7.5f, 5.5f))
        }
    }
    val Palette by lazy {
        icon("Palette") {
            stroke("M12 3a9 9 0 1 0 0 18c1.1 0 1.8-.8 1.8-1.8 0-.5-.2-.9-.5-1.3-.3-.3-.5-.8-.5-1.3 0-1 .8-1.8 1.8-1.8H17a4 4 0 0 0 4-4C21 6.7 17 3 12 3Z")
            dots(7.5f to 12f, 9.5f to 7.8f, 14.5f to 7.8f, r = 1.25f)
        }
    }
    val Laundry by lazy {
        icon("Laundry") {
            stroke(rr(4f, 3f, 16f, 18f, 2f))
            stroke("M4 7.5h16")
            circle(12f, 14f, 4f)
            stroke("M9.8 14.6c.7-.5 1.5-.5 2.2 0s1.5.5 2.2 0")
            dots(7f to 5.25f, 9.5f to 5.25f, r = 0.9f)
        }
    }
    val Luggage by lazy {
        icon("Luggage") {
            stroke(rr(5f, 7f, 14f, 13f, 2.5f))
            stroke("M9 7V4.5a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1V7 M9.5 10.5v6 M14.5 10.5v6 M8 20v1.5 M16 20v1.5")
        }
    }
    val Flame by lazy {
        icon("Flame") {
            stroke("M12 21a6.5 6.5 0 0 0 6.5-6.5c0-4.2-3-6.3-4.3-10.5-1.5 1.4-2.6 3.3-2.6 5.6-1-.8-1.6-1.9-1.8-3.3C7.6 8.4 5.5 11 5.5 14.5A6.5 6.5 0 0 0 12 21Z")
            stroke("M12 21a2.8 2.8 0 0 1-2.8-2.8c0-1.6 1.3-2.5 1.9-3.9 1 1 3.7 2 3.7 4 0 1.5-1.2 2.7-2.8 2.7Z")
        }
    }
    val Bulb by lazy {
        icon("Bulb") {
            stroke("M9 17.5h6 M10 21h4")
            stroke("M12 3a6 6 0 0 0-3.6 10.8c.6.5 1.1 1.2 1.1 2v.2h5v-.2c0-.8.5-1.5 1.1-2A6 6 0 0 0 12 3Z")
        }
    }
    val Camera by lazy {
        icon("Camera") {
            stroke("M3 9a2 2 0 0 1 2-2h2.4l1.5-2.5h6.2L16.6 7H19a2 2 0 0 1 2 2v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2Z")
            circle(12f, 13.2f, 3.6f)
        }
    }
    val Photos by lazy {
        icon("Photos") {
            stroke("M7.5 3.5h11a2 2 0 0 1 2 2v11")
            stroke(rr(3.5f, 7f, 14f, 13.5f, 2f))
            stroke("M3.5 17.5l3.7-3.7a1.2 1.2 0 0 1 1.7 0l4.6 4.6 M12.3 15.7l1.2-1.2a1.2 1.2 0 0 1 1.7 0l2.3 2.3")
            dots(13f to 10.8f, r = 1.1f)
        }
    }
    val Brush by lazy {
        icon("Brush") {
            stroke("M10 14 19.3 4.7a1.9 1.9 0 0 1 2.7 2.7L12.7 16.7")
            stroke("M10 14c-2.2-.1-3.8 1.3-3.8 3.4 0 1.2-.8 2.1-2.4 2.1 1.1 1 2.5 1.5 4 1.5 2.6 0 4.6-1.9 4.9-4.3")
        }
    }
    val Scissors by lazy {
        icon("Scissors") {
            circle(6f, 6.5f, 2.5f); circle(6f, 17.5f, 2.5f)
            stroke("M8.1 8 20 18 M8.1 16 20 6")
        }
    }

    // ---- Weather ----
    val Sun get() = Today
    val PartlyCloudy by lazy {
        icon("PartlyCloudy") {
            stroke("M8.5 2v1.5 M3.2 4.2l1 1 M2 9.5h1.5 M13.8 4.2l-1 1")
            stroke("M5.4 12.2A3.6 3.6 0 1 1 12.3 7.9")
            stroke("M9 20A3.5 3.5 0 0 1 8.6 13A5 5 0 0 1 18 12.4A3.8 3.8 0 0 1 17.5 20Z")
        }
    }
    val Cloud by lazy { icon("Cloud") { stroke(CLOUD) } }
    val Fog by lazy { icon("Fog") { stroke("M7 13.5A4 4 0 0 1 6.5 5.6A5.5 5.5 0 0 1 17 5.4A4 4 0 0 1 17 13.5Z"); stroke("M3.5 17h17 M6 20.5h12") } }
    val Drizzle by lazy { icon("Drizzle") { stroke(CLOUD_HIGH); stroke("M8 18.5v.5 M12 19.5v.5 M16 18.5v.5 M10 21.5v.01 M14 21.5v.01", width = 2f) } }
    val Rain by lazy { icon("Rain") { stroke(CLOUD_HIGH); stroke("M8.5 17.5 7.3 21 M12.5 17.5 11.3 21 M16.5 17.5 15.3 21") } }
    val Snow by lazy { icon("Snow") { stroke(CLOUD_HIGH); dots(8f to 18.5f, 12f to 18f, 16f to 18.5f, 10f to 21.2f, 14f to 21.2f, r = 1.05f) } }
    val Storm by lazy { icon("Storm") { stroke(CLOUD_HIGH); stroke("M12.8 14.5 10.5 18h3l-2.3 3.5") } }
    val Thermometer by lazy { icon("Thermometer") { stroke("M14 14.8V5a2 2 0 0 0-4 0v9.8a4 4 0 1 0 4 0Z M12 10.5v6.5") } }
    val Location by lazy { icon("Location") { stroke("M12 21.5s-7-6.1-7-11.7a7 7 0 0 1 14 0c0 5.6-7 11.7-7 11.7Z"); circle(12f, 9.8f, 2.6f) } }
    val MyLocation by lazy { icon("MyLocation") { circle(12f, 12f, 7f); dots(12f to 12f, r = 2.2f); stroke("M12 2v3 M12 19v3 M2 12h3 M19 12h3") } }

    // ---- Common actions ----
    val Back by lazy { icon("Back", autoMirror = true) { stroke("M19.5 12h-15 M10.5 18l-6-6 6-6") } }
    val Forward by lazy { icon("Forward", autoMirror = true) { stroke("M4.5 12h15 M13.5 6l6 6-6 6") } }
    val ChevronLeft by lazy { icon("ChevronLeft", autoMirror = true) { stroke("M15 18l-6-6 6-6") } }
    val ChevronRight by lazy { icon("ChevronRight", autoMirror = true) { stroke("M9 18l6-6-6-6") } }
    val ChevronDown by lazy { icon("ChevronDown") { stroke("M6 9l6 6 6-6") } }
    val ChevronUp by lazy { icon("ChevronUp") { stroke("M18 15l-6-6-6 6") } }
    val Close by lazy { icon("Close") { stroke("M18 6 6 18 M6 6l12 12") } }
    val Check by lazy { icon("Check") { stroke("M20 6.5 9.2 17.5 4 12.3", width = 2.1f) } }
    val Add by lazy { icon("Add") { stroke("M12 5v14 M5 12h14") } }
    val Remove by lazy { icon("Remove") { stroke("M5 12h14") } }
    val Search by lazy { icon("Search") { circle(10.75f, 10.75f, 6.75f); stroke("M20 20l-4.25-4.25") } }
    val More by lazy { icon("More") { dots(12f to 5.5f, 12f to 12f, 12f to 18.5f, r = 1.6f) } }
    val Sort by lazy { icon("Sort") { stroke("M4 6.5h16 M7 12h10 M10 17.5h4") } }
    val Notes by lazy { icon("Notes", autoMirror = true) { stroke("M4 6h16 M4 12h16 M4 18h10") } }
    val Grid by lazy {
        icon("Grid") {
            stroke(rr(3.5f, 3.5f, 7.5f, 7.5f)); stroke(rr(13f, 3.5f, 7.5f, 7.5f))
            stroke(rr(3.5f, 13f, 7.5f, 7.5f)); stroke(rr(13f, 13f, 7.5f, 7.5f))
        }
    }
    val Edit by lazy { icon("Edit") { stroke(PENCIL); stroke("M14 6l4 4") } }
    val Rename by lazy { icon("Rename") { stroke("M13.5 20.5h7"); stroke(PENCIL) } }
    val Delete by lazy {
        icon("Delete") {
            stroke("M4 6.5h16 M9.5 6.5v-2a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v2")
            stroke("M6 6.5l.9 12.6a2 2 0 0 0 2 1.9h6.2a2 2 0 0 0 2-1.9L18 6.5 M10 11v5.5 M14 11v5.5")
        }
    }
    val Refresh by lazy {
        icon("Refresh") {
            stroke("M4 12a8 8 0 0 1 13.7-5.6L20 8.5 M20 4v4.5h-4.5")
            stroke("M20 12a8 8 0 0 1-13.7 5.6L4 15.5 M4 20v-4.5h4.5")
        }
    }
    val Undo by lazy { icon("Undo", autoMirror = true) { stroke("M9 14 4 9l5-5 M4 9h10.5a5.5 5.5 0 0 1 0 11H11") } }
    val Export by lazy { icon("Export") { stroke("M12 3.5v11 M7.5 10l4.5 4.5 4.5-4.5 M4 15.5v3a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2v-3") } }
    val Restore by lazy {
        icon("Restore") {
            stroke("M3.5 12a8.5 8.5 0 1 0 2.6-6.1L3.5 8.5 M3.5 3.5v5h5")
            stroke("M12 7.5V12l3 2")
        }
    }
    val Settings by lazy {
        icon("Settings") {
            stroke("M4 6.5h9 M17 6.5h3 M4 12h3 M11 12h9 M4 17.5h11 M19 17.5h1")
            circle(15f, 6.5f, 2f); circle(9f, 12f, 2f); circle(17f, 17.5f, 2f)
        }
    }
    val Clock by lazy { icon("Clock") { circle(12f, 12f, 9f); stroke("M12 7v5l3.2 2") } }
    val Keyboard by lazy {
        icon("Keyboard") {
            stroke(rr(2.5f, 6f, 19f, 12f, 2.5f))
            stroke("M7 14.5h10")
            dots(7f to 10f, 10.3f to 10f, 13.6f to 10f, 17f to 10f, r = 0.95f)
        }
    }
    val Bell by lazy { icon("Bell") { stroke(BELL); stroke("M10 20.5a2.2 2.2 0 0 0 4 0") } }
    val BellOff by lazy { icon("BellOff") { stroke(BELL); stroke("M10 20.5a2.2 2.2 0 0 0 4 0 M3 3l18 18") } }
    val Lock by lazy { icon("Lock") { stroke(rr(5f, 10.5f, 14f, 10.5f, 2f)); stroke("M8 10.5V7.5a4 4 0 0 1 8 0v3"); dots(12f to 15.75f, r = 1.25f) } }
    val LockOpen by lazy { icon("LockOpen") { stroke(rr(5f, 10.5f, 14f, 10.5f, 2f)); stroke("M8 10.5V7.5a4 4 0 0 1 7.8-1.3"); dots(12f to 15.75f, r = 1.25f) } }
    val Price by lazy {
        icon("Price") {
            stroke("M3.5 12.2V5a1.5 1.5 0 0 1 1.5-1.5h7.2a1.5 1.5 0 0 1 1 .4l7.4 7.4a1.5 1.5 0 0 1 0 2.1l-7.2 7.2a1.5 1.5 0 0 1-2.1 0L3.9 13.2a1.5 1.5 0 0 1-.4-1Z")
            dots(8f to 8f, r = 1.4f)
        }
    }
    val ZoomIn by lazy { icon("ZoomIn") { circle(10.75f, 10.75f, 6.75f); stroke("M20 20l-4.25-4.25 M10.75 8v5.5 M8 10.75h5.5") } }
    val ZoomOut by lazy { icon("ZoomOut") { circle(10.75f, 10.75f, 6.75f); stroke("M20 20l-4.25-4.25 M8 10.75h5.5") } }
    val BringForward by lazy {
        icon("BringForward") {
            stroke(rr(9f, 3f, 12f, 12f, 1.5f))
            stroke("M6 9H4.5A1.5 1.5 0 0 0 3 10.5v9A1.5 1.5 0 0 0 4.5 21h9a1.5 1.5 0 0 0 1.5-1.5V18")
        }
    }
    val SendBackward by lazy {
        icon("SendBackward") {
            stroke(rr(3f, 9f, 12f, 12f, 1.5f))
            stroke("M9 6V4.5A1.5 1.5 0 0 1 10.5 3h9A1.5 1.5 0 0 1 21 4.5v9a1.5 1.5 0 0 1-1.5 1.5H18")
        }
    }

    // ---- Status and feedback ----
    val CheckCircle by lazy { icon("CheckCircle") { circle(12f, 12f, 9f); stroke("M8 12.3l2.8 2.8L16.2 9.5") } }
    val Circle by lazy { icon("Circle") { circle(12f, 12f, 9f) } }
    val Warning by lazy {
        icon("Warning") {
            stroke("M10.3 4.2 2.7 17.4A2 2 0 0 0 4.4 20.4h15.2a2 2 0 0 0 1.7-3L13.7 4.2a2 2 0 0 0-3.4 0Z M12 9.5v4")
            dots(12f to 16.9f, r = 1.1f)
        }
    }
    val Heart by lazy { icon("Heart") { stroke(HEART) } }
    val HeartFilled by lazy { icon("HeartFilled") { fill(HEART); stroke(HEART) } }
    val Bookmark by lazy { icon("Bookmark") { stroke(BOOKMARK) } }
    val BookmarkFilled by lazy { icon("BookmarkFilled") { fill(BOOKMARK); stroke(BOOKMARK) } }
    val BookmarkAdd by lazy { icon("BookmarkAdd") { stroke(BOOKMARK); stroke("M12 7v6 M9 10h6") } }
    val Bookmarks by lazy {
        icon("Bookmarks") {
            stroke("M9 3.5h9a1.5 1.5 0 0 1 1.5 1.5v13")
            stroke("M5.5 7.5h8a1.5 1.5 0 0 1 1.5 1.5v12l-5.25-3.5L4.5 21V8.5a1 1 0 0 1 1-1Z")
        }
    }
    val ThumbUp by lazy { icon("ThumbUp") { stroke(THUMB) } }
    val ThumbUpFilled by lazy { icon("ThumbUpFilled") { fill(THUMB); stroke(THUMB) } }
    val ThumbDown by lazy { icon("ThumbDown") { flipped { stroke(THUMB) } } }
    val ThumbDownFilled by lazy { icon("ThumbDownFilled") { flipped { fill(THUMB); stroke(THUMB) } } }

    // ---- Shared path data ----
    private const val HANGER_HOOK = "M12 8.2V7.4a2.2 2.2 0 1 0-2.2-2.2"
    private const val HANGER_BODY = "M12 8.2 3.1 15.1A1.6 1.6 0 0 0 4.1 18h15.8a1.6 1.6 0 0 0 1-2.9Z"
    private const val TEE = "M8.3 3.5 4.2 5.4a1.5 1.5 0 0 0-.8.9L2.5 9.6l3.4 1.5L7 9.6V19.5a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V9.6l1.1 1.5 3.4-1.5-.9-3.3a1.5 1.5 0 0 0-.8-.9L15.7 3.5a3.8 3.8 0 0 1-7.4 0Z"
    private const val CALENDAR = "M5.5 4.5h13a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2h-13a2 2 0 0 1-2-2v-12a2 2 0 0 1 2-2Z M3.5 10h17 M8 2.5v4 M16 2.5v4"
    private const val BARS = "M4.5 20.5V13a1.5 1.5 0 0 1 1.5-1.5h1A1.5 1.5 0 0 1 8.5 13v7.5Z M10 20.5V5a1.5 1.5 0 0 1 1.5-1.5h1A1.5 1.5 0 0 1 14 5v15.5Z M15.5 20.5V9.5A1.5 1.5 0 0 1 17 8h1a1.5 1.5 0 0 1 1.5 1.5v11Z"
    private const val CLOUD = "M7 18.5A4.5 4.5 0 0 1 6.4 9.5A6 6 0 0 1 17.8 9.1A4.7 4.7 0 0 1 17.3 18.5Z"
    private const val CLOUD_HIGH = "M7 15A4.3 4.3 0 0 1 6.5 6.4A5.8 5.8 0 0 1 17.6 6A4.5 4.5 0 0 1 17.2 15Z"
    private const val PENCIL = "M15.6 4.4a2.1 2.1 0 0 1 3 3L7.5 18.5 3.5 19.5l1-4Z"
    private const val BELL = "M6 10a6 6 0 0 1 12 0c0 5.5 2.3 7.5 2.3 7.5H3.7S6 15.5 6 10Z"
    private const val HEART = "M12 20s-7.6-4.5-9-9.3C2 7.2 4.3 4.5 7.3 4.5c2 0 3.6 1.1 4.7 2.8 1.1-1.7 2.7-2.8 4.7-2.8 3 0 5.3 2.7 4.3 6.2C19.6 15.5 12 20 12 20Z"
    private const val BOOKMARK = "M7 3.5h10a1.5 1.5 0 0 1 1.5 1.5v16L12 16.8 5.5 21V5A1.5 1.5 0 0 1 7 3.5Z"
    private const val THUMB = "M7.5 10.5 11.3 3.6a2.2 2.2 0 0 1 3.9 1.7l-.7 4.2h4.6a2 2 0 0 1 2 2.4l-1.3 6.9a2 2 0 0 1-2 1.7H7.5Z M7.5 10.5H4.5a1 1 0 0 0-1 1v7.5a1 1 0 0 0 1 1h3"

    /** Rounded rectangle path. */
    private fun rr(x: Float, y: Float, w: Float, h: Float, r: Float = 1.5f): String =
        "M${x + r} ${y}h${w - 2 * r}a$r $r 0 0 1 $r ${r}v${h - 2 * r}a$r $r 0 0 1-$r ${r}h-${w - 2 * r}a$r $r 0 0 1-$r-${r}v-${h - 2 * r}a$r $r 0 0 1 $r-${r}Z"
}

/** Default line weight for the set. */
private const val STROKE = 1.75f

private fun circlePath(cx: Float, cy: Float, r: Float): String =
    "M${cx - r} ${cy}a$r $r 0 1 0 ${2 * r} 0a$r $r 0 1 0-${2 * r} 0Z"

private class IconScope(private val builder: ImageVector.Builder) {
    // A fresh parser per path: PathParser reuses its node list, so sharing one would overwrite earlier paths.
    private fun nodes(d: String) = PathParser().parsePathString(d).toNodes()

    fun stroke(d: String, width: Float = STROKE) {
        builder.addPath(
            pathData = nodes(d),
            fill = null,
            stroke = SolidColor(Color.Black),
            strokeLineWidth = width,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round,
        )
    }

    fun fill(d: String) {
        builder.addPath(pathData = nodes(d), fill = SolidColor(Color.Black))
    }

    fun circle(cx: Float, cy: Float, r: Float) = stroke(circlePath(cx, cy, r))

    fun dots(vararg points: Pair<Float, Float>, r: Float = 1.15f) = points.forEach { (x, y) -> fill(circlePath(x, y, r)) }

    /** Draws [block] flipped top-to-bottom (e.g. thumbs down from thumbs up). */
    fun flipped(block: IconScope.() -> Unit) {
        builder.addGroup(name = "flip", scaleY = -1f, pivotX = 12f, pivotY = 12f)
        block()
        builder.clearGroup()
    }
}

private fun icon(name: String, autoMirror: Boolean = false, block: IconScope.() -> Unit): ImageVector {
    val builder = ImageVector.Builder(
        name = "Hanger.$name",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f,
        autoMirror = autoMirror,
    )
    IconScope(builder).block()
    return builder.build()
}
