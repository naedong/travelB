package kr.tr.commom.utill

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-04
 * Time: 오후 3:07
 */

@Composable
fun <S> MaterialMotion(
    targetState: S,
    transitionSpec: AnimatedContentTransitionScope<S>.() -> ContentTransform,
    modifier: Modifier = Modifier,
    pop: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable AnimatedVisibilityScope.(targetState: S) -> Unit,
) {
    val transition = updateTransition(targetState = targetState, label = "MaterialMotion")
    transition.MaterialMotion(
        transitionSpec,
        modifier,
        pop,
        contentAlignment,
        content = content
    )
}

/**
 * [MaterialMotion] allows to switch between two layouts with a material motion animation.
 *
 * @param transitionSpec the [ContentTransform] to configure the enter/exit animation.
 * @param modifier Modifier to be applied to the animation container.
 * @param pop whether motion contents are rendered in reverse order.
 */
@Composable
fun <S> Transition<S>.MaterialMotion(
    transitionSpec: AnimatedContentTransitionScope<S>.() -> ContentTransform,
    modifier: Modifier = Modifier,
    pop: Boolean = false,
    contentAlignment: Alignment = Alignment.TopStart,
    contentKey: (targetState: S) -> Any? = { it },
    content: @Composable AnimatedVisibilityScope.(targetState: S) -> Unit,
) {
    val forward: Boolean = pop.not()
    val contentZIndex = remember { mutableStateOf(0f) }
    AnimatedContent(
        modifier = modifier,
        transitionSpec = {
            val spec = transitionSpec()
            (spec.targetContentEnter togetherWith spec.initialContentExit)
                .apply {
                    // Show forward contents over the backward contents.
                    if (forward) {
                        contentZIndex.value += 0.0001f
                    } else {
                        contentZIndex.value -= 0.0001f
                    }
                    targetContentZIndex = contentZIndex.value
                }
        },
        contentAlignment = contentAlignment,
        contentKey = contentKey
    ) { currentState ->
        content(currentState)
    }
}