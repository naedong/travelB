package kr.tr.commom.utill

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.DialogHost
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.LocalOwnersProvider
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.get
import kotlinx.coroutines.flow.map
import kr.tr.commom.model.MotionConstants

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-04
 * Time: 오후 4:15
 */
@ExperimentalAnimationApi
@Composable
fun rememberMaterialMotionNavController(
    vararg navigators: Navigator<out NavDestination>,
): NavHostController {
    val navigator = remember { MaterialMotionComposeNavigator() }
    return rememberNavController(navigator, *navigators)
}

@Composable
@ExperimentalAnimationApi
fun MaterialMotionNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    route: String? = null,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(animationSpec = tween(MotionConstants.DefaultMotionDuration))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(animationSpec = tween(MotionConstants.DefaultMotionDuration))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
    builder: NavGraphBuilder.() -> Unit,
) {
    MaterialMotionNavHost(
        navController,
        remember(route, startDestination, builder) {
            navController.createGraph(startDestination, route, builder)
        },
        modifier,
        contentAlignment,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition
    )
}

/**
 * Provides in place in the Compose hierarchy for self contained navigation to occur.
 *
 * Once this is called, any Composable within the given [NavGraphBuilder] can be navigated to from
 * the provided [navController].
 *
 * @param navController the navController for this host
 * @param graph the graph for this host
 * @param modifier The modifier to be applied to the layout.
 * @param enterTransition callback to define enter transitions for destination in this host
 * @param exitTransition callback to define exit transitions for destination in this host
 * @param popEnterTransition callback to define popEnter transitions for destination in this host
 * @param popExitTransition callback to define popExit transitions for destination in this host
 */
@ExperimentalAnimationApi
@Composable
public fun MaterialMotionNavHost(
    navController: NavHostController,
    graph: NavGraph,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        fadeIn(animationSpec = tween(MotionConstants.DefaultMotionDuration))
    },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        fadeOut(animationSpec = tween(MotionConstants.DefaultMotionDuration))
    },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "NavHost requires a ViewModelStoreOwner to be provided via LocalViewModelStoreOwner"
    }
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    val onBackPressedDispatcher = onBackPressedDispatcherOwner?.onBackPressedDispatcher

    // on successful recompose we setup the navController with proper inputs
    // after the first time, this will only happen again if one of the inputs changes
    navController.setLifecycleOwner(lifecycleOwner)
    navController.setViewModelStore(viewModelStoreOwner.viewModelStore)
    if (onBackPressedDispatcher != null) {
        navController.setOnBackPressedDispatcher(onBackPressedDispatcher)
    }

    navController.graph = graph

    val saveableStateHolder = rememberSaveableStateHolder()

    // Find the ComposeNavigator, returning early if it isn't found
    // (such as is the case when using TestNavHostController)
    val composeNavigator = navController.navigatorProvider.get<Navigator<out NavDestination>>(
        MaterialMotionComposeNavigator.NAME
    ) as? MaterialMotionComposeNavigator ?: return
    val visibleEntries by remember(navController.visibleEntries) {
        navController.visibleEntries.map {
            it.filter { entry ->
                entry.destination.navigatorName == MaterialMotionComposeNavigator.NAME
            }
        }
    }.collectAsState(emptyList())

    val backStackEntry = visibleEntries.lastOrNull()
    if (backStackEntry != null) {
        val finalEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
            val targetDestination =
                targetState.destination as MaterialMotionComposeNavigator.Destination
            if (composeNavigator.isPop.value) {
                targetDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    popEnterTransitions[destination.route]?.invoke(this)
                } ?: popEnterTransition.invoke(this)
            } else {
                targetDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    enterTransitions[destination.route]?.invoke(this)
                } ?: enterTransition.invoke(this)
            }
        }

        val finalExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
            val initialDestination =
                initialState.destination as MaterialMotionComposeNavigator.Destination
            if (composeNavigator.isPop.value) {
                initialDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    popExitTransitions[destination.route]?.invoke(this)
                } ?: popExitTransition.invoke(this)
            } else {
                initialDestination.hierarchy.firstNotNullOfOrNull { destination ->
                    exitTransitions[destination.route]?.invoke(this)
                } ?: exitTransition.invoke(this)
            }
        }

        val transition = updateTransition(backStackEntry, label = "entry")
        transition.MaterialMotion(
            transitionSpec = {
                // If the initialState of the AnimatedContent is not in visibleEntries, we are in
                // a case where visible has cleared the old state for some reason, so instead of
                // attempting to animate away from the initialState, we skip the animation.
                if (initialState in visibleEntries) {
                    finalEnter(this) togetherWith finalExit(this)
                } else {
                    EnterTransition.None togetherWith ExitTransition.None
                }
            },
            modifier = modifier,
            pop = composeNavigator.isPop.value,
            contentAlignment = contentAlignment,
            contentKey = { it.id }
        ) {
            // In some specific cases, such as clearing your back stack by changing your
            // start destination, AnimatedContent can contain an entry that is no longer
            // part of visible entries since it was cleared from the back stack and is not
            // animating. In these cases the currentEntry will be null, and in those cases,
            // AnimatedContent will just skip attempting to transition the old entry.
            // See https://issuetracker.google.com/238686802
            val currentEntry = visibleEntries.lastOrNull { entry ->
                it == entry
            }
            // while in the scope of the composable, we provide the navBackStackEntry as the
            // ViewModelStoreOwner and LifecycleOwner
            currentEntry?.LocalOwnersProvider(saveableStateHolder) {
                (currentEntry.destination as MaterialMotionComposeNavigator.Destination)
                    .content(this, currentEntry)
            }
        }
        if (transition.currentState == transition.targetState) {
            visibleEntries.forEach { entry ->
                composeNavigator.markTransitionComplete(entry)
            }
        }
    }

    val dialogNavigator = navController.navigatorProvider.get<Navigator<out NavDestination>>(
        "dialog"
    ) as? DialogNavigator ?: return

    // Show any dialog destinations
    DialogHost(dialogNavigator)
}

@ExperimentalAnimationApi
internal val enterTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?>()

@ExperimentalAnimationApi
internal val exitTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?>()

@ExperimentalAnimationApi
internal val popEnterTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?>()

@ExperimentalAnimationApi
internal val popExitTransitions =
    mutableMapOf<String?, (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?>()

