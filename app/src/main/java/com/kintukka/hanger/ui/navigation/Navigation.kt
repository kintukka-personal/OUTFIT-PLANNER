package com.kintukka.hanger.ui.navigation

import com.kintukka.hanger.ui.icons.HangerIcons

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kintukka.hanger.ui.components.Hanger
import com.kintukka.hanger.ui.components.LocalWindowWidth
import com.kintukka.hanger.ui.components.WindowWidth
import com.kintukka.hanger.ui.components.windowWidthFor
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kintukka.hanger.ui.closet.AddItemsScreen
import com.kintukka.hanger.ui.closet.ClosetScreen
import com.kintukka.hanger.ui.closet.EditItemScreen
import com.kintukka.hanger.ui.closet.ItemDetailScreen
import com.kintukka.hanger.ui.insights.InsightsScreen
import com.kintukka.hanger.ui.outfits.CapsuleScreen
import com.kintukka.hanger.ui.outfits.OutfitBuilderScreen
import com.kintukka.hanger.ui.outfits.OutfitDetailScreen
import com.kintukka.hanger.ui.outfits.OutfitsScreen
import com.kintukka.hanger.ui.outfits.ShuffleScreen
import com.kintukka.hanger.ui.outfits.SuggestScreen
import com.kintukka.hanger.ui.planner.DayScreen
import com.kintukka.hanger.ui.planner.PlannerScreen
import com.kintukka.hanger.ui.planner.TripEditScreen
import com.kintukka.hanger.ui.planner.TripScreen
import com.kintukka.hanger.ui.settings.SettingsScreen
import com.kintukka.hanger.ui.today.TodayScreen
import kotlinx.serialization.Serializable
import java.time.LocalDate
import kotlin.reflect.KClass

const val NO_DATE = Long.MIN_VALUE

@Serializable object TodayRoute
@Serializable object ClosetRoute
@Serializable object OutfitsRoute
@Serializable object PlannerRoute
@Serializable object InsightsRoute
@Serializable object SettingsRoute
@Serializable object AddItemsRoute
@Serializable object StyleGuideRoute
@Serializable data class ItemRoute(val id: Long)
@Serializable data class EditItemRoute(val id: Long)
@Serializable data class OutfitRoute(val id: Long)
@Serializable data class CapsuleRoute(val id: Long)
@Serializable data class DayRoute(val dateEpoch: Long)
@Serializable data class TripRoute(val id: Long)
@Serializable data class TripEditRoute(val id: Long = 0)

/** Canvas builder. Prefill with an existing outfit, or a list of items, optionally for a calendar day. */
@Serializable data class BuilderRoute(
    val outfitId: Long = 0,
    val dateEpoch: Long = NO_DATE,
    val itemIds: String = "",
    /** When editing a calendar look, the entry to update instead of adding a new one. */
    val entryId: Long = 0,
)

@Serializable data class ShuffleRoute(val dateEpoch: Long = NO_DATE)

/** Generated suggestions, optionally around one anchor item and/or for a calendar day. */
@Serializable data class SuggestRoute(val dateEpoch: Long = NO_DATE, val anchorItemId: Long = 0)

fun Long.toDateOrNull(): LocalDate? = if (this == NO_DATE) null else LocalDate.ofEpochDay(this)
fun LocalDate?.toEpochOrNone(): Long = this?.toEpochDay() ?: NO_DATE

private data class Tab(val route: Any, val routeClass: KClass<*>, val label: String, val icon: ImageVector, val selectedIcon: ImageVector)

private val tabs = listOf(
    Tab(TodayRoute, TodayRoute::class, "Today", HangerIcons.Sun, HangerIcons.TodayFilled),
    Tab(ClosetRoute, ClosetRoute::class, "Closet", HangerIcons.Hanger, HangerIcons.HangerFilled),
    Tab(OutfitsRoute, OutfitsRoute::class, "Outfits", HangerIcons.Outfit, HangerIcons.OutfitFilled),
    Tab(PlannerRoute, PlannerRoute::class, "Plan", HangerIcons.Calendar, HangerIcons.CalendarFilled),
    Tab(InsightsRoute, InsightsRoute::class, "Insights", HangerIcons.Insights, HangerIcons.InsightsFilled),
)

@Composable
fun HangerNavHost(navController: NavHostController = rememberNavController()) {
    val backStack by navController.currentBackStackEntryAsState()
    val destination = backStack?.destination
    val onTopLevel = tabs.any { tab -> destination?.hierarchy?.any { it.hasRoute(tab.routeClass) } == true }
    val isSelected: (Tab) -> Boolean = { tab -> destination?.hierarchy?.any { it.hasRoute(tab.routeClass) } == true }
    val go: (Tab) -> Unit = { tab ->
        navController.navigate(tab.route) {
            popUpTo(navController.graph.findStartDestination().id) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    BoxWithConstraints(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        val widthClass = windowWidthFor(maxWidth)
        // Rail on tablets/foldables, and on phones in landscape where a bottom bar eats scarce height.
        val useRail = widthClass != WindowWidth.Compact || (maxWidth > maxHeight && maxHeight < 480.dp)
        CompositionLocalProvider(LocalWindowWidth provides widthClass) {
            Row(Modifier.fillMaxSize()) {
                if (useRail && onTopLevel) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                        modifier = Modifier.fillMaxHeight(),
                        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Vertical + WindowInsetsSides.Start),
                    ) {
                        Spacer(Modifier.weight(1f))
                        tabs.forEach { tab ->
                            val selected = isSelected(tab)
                            NavigationRailItem(
                                selected = selected,
                                onClick = { go(tab) },
                                icon = { Icon(if (selected) tab.selectedIcon else tab.icon, contentDescription = null) },
                                label = { Text(tab.label) },
                                modifier = Modifier.testTag("tab-${tab.label}").padding(vertical = 4.dp),
                                colors = NavigationRailItemDefaults.colors(
                                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                ),
                            )
                        }
                        Spacer(Modifier.weight(1f))
                    }
                    VerticalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
                Scaffold(
                    contentWindowInsets = WindowInsets(0),
                    modifier = Modifier.weight(1f),
                    bottomBar = {
                        if (!useRail && onTopLevel) {
                            Column {
                                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                                NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainerLow, tonalElevation = 0.dp) {
                                    tabs.forEach { tab ->
                                        val selected = isSelected(tab)
                                        NavigationBarItem(
                                            selected = selected,
                                            onClick = { go(tab) },
                                            icon = { Icon(if (selected) tab.selectedIcon else tab.icon, contentDescription = null) },
                                            label = { Text(tab.label, style = MaterialTheme.typography.labelMedium.copy(letterSpacing = 0.2.sp)) },
                                            modifier = Modifier.testTag("tab-${tab.label}"),
                                            colors = NavigationBarItemDefaults.colors(
                                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                            ),
                                        )
                                    }
                                }
                            }
                        }
                    },
                ) { padding ->
                    Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.TopCenter) {
                        HangerNavGraph(navController, Modifier.widthIn(max = Hanger.MaxContentWidth).fillMaxSize())
                    }
                }
            }
        }
    }
}

@Composable
private fun HangerNavGraph(navController: NavHostController, modifier: Modifier) {
        NavHost(
            navController = navController,
            startDestination = TodayRoute,
            modifier = modifier,
            enterTransition = { fadeIn(tween(220)) + slideInVertically(tween(260)) { it / 40 } },
            exitTransition = { fadeOut(tween(160)) },
            popEnterTransition = { fadeIn(tween(220)) },
            popExitTransition = { fadeOut(tween(160)) + slideOutVertically(tween(200)) { it / 40 } },
        ) {
            composable<TodayRoute> { TodayScreen(navController) }
            composable<ClosetRoute> { ClosetScreen(navController) }
            composable<OutfitsRoute> { OutfitsScreen(navController) }
            composable<PlannerRoute> { PlannerScreen(navController) }
            composable<InsightsRoute> { InsightsScreen(navController) }
            composable<SettingsRoute> { SettingsScreen(navController) }
            composable<AddItemsRoute> { AddItemsScreen(navController) }
            composable<StyleGuideRoute> { com.kintukka.hanger.ui.guide.StyleGuideScreen(navController) }
            composable<ItemRoute> { ItemDetailScreen(navController, it.toRoute<ItemRoute>().id) }
            composable<EditItemRoute> { EditItemScreen(navController, it.toRoute<EditItemRoute>().id) }
            composable<OutfitRoute> { OutfitDetailScreen(navController, it.toRoute<OutfitRoute>().id) }
            composable<CapsuleRoute> { CapsuleScreen(navController, it.toRoute<CapsuleRoute>().id) }
            composable<DayRoute> { DayScreen(navController, LocalDate.ofEpochDay(it.toRoute<DayRoute>().dateEpoch)) }
            composable<TripRoute> { TripScreen(navController, it.toRoute<TripRoute>().id) }
            composable<TripEditRoute> { TripEditScreen(navController, it.toRoute<TripEditRoute>().id) }
            composable<BuilderRoute>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
            ) {
                val r = it.toRoute<BuilderRoute>()
                OutfitBuilderScreen(
                    navController,
                    outfitId = r.outfitId,
                    date = r.dateEpoch.toDateOrNull(),
                    initialItemIds = r.itemIds.split(",").mapNotNull(String::toLongOrNull),
                    entryId = r.entryId,
                )
            }
            composable<ShuffleRoute> { ShuffleScreen(navController, it.toRoute<ShuffleRoute>().dateEpoch.toDateOrNull()) }
            composable<SuggestRoute> {
                val r = it.toRoute<SuggestRoute>()
                SuggestScreen(navController, r.dateEpoch.toDateOrNull(), r.anchorItemId.takeIf { id -> id > 0 })
            }
    }
}
