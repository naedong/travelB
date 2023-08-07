package kr.tr.commom.utill

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-04
 * Time: 오후 4:16
 */

@ExperimentalAnimationApi
@Navigator.Name("materialMotionComposable")
public class MaterialMotionComposeNavigator : Navigator<MaterialMotionComposeNavigator.Destination>() {
    internal val transitionsInProgress get() = state.transitionsInProgress

    internal val backStack get() = state.backStack

    internal val isPop = mutableStateOf(false)

    override fun navigate(
        entries: List<NavBackStackEntry>,
        navOptions: NavOptions?,
        navigatorExtras: Extras?,
    ) {
        entries.forEach { entry ->
            state.pushWithTransition(entry)
        }
        isPop.value = false
    }

    override fun createDestination(): Destination {
        return Destination(this, content = { })
    }

    override fun popBackStack(popUpTo: NavBackStackEntry, savedState: Boolean) {
        state.popWithTransition(popUpTo, savedState)
        isPop.value = true
    }

    internal fun markTransitionComplete(entry: NavBackStackEntry) {
        state.markTransitionComplete(entry)
    }

    /**
     * NavDestination specific to [MaterialMotionComposeNavigator]
     */
    @ExperimentalAnimationApi
    @NavDestination.ClassType(Composable::class)
    public class Destination(
        navigator: MaterialMotionComposeNavigator,
        internal val content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) : NavDestination(navigator)

    internal companion object {
        internal const val NAME = "materialMotionComposable"
    }
}
