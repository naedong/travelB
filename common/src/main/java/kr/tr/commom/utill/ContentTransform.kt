package kr.tr.commom.utill

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kr.tr.commom.model.MotionConstants

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-04
 * Time: 오후 3:17
 */

class ContentTransform(
    val targetContentEnter: EnterTransition,
    val initialContentExit: ExitTransition,
    targetContentZIndex: Float = 0f,
    sizeTransform: SizeTransform? = SizeTransform()
) {

    var targetContentZIndex by mutableFloatStateOf(targetContentZIndex)

    var sizeTransform: SizeTransform? = sizeTransform
        internal set
}

infix fun EnterTransition.togetherWith(exit: ExitTransition) = ContentTransform(this, exit)

@Composable
fun rememberSlideDistance(
    slideDistance: Dp = MotionConstants.DefaultSlideDistance,
): Int {
    val density = LocalDensity.current
    return remember(density, slideDistance) {
        with(density) { slideDistance.roundToPx() }
    }
}

fun materialSharedAxisX(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisXIn(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
) togetherWith materialSharedAxisXOut(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
)

fun materialSharedAxisXIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = slideInHorizontally(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    initialOffsetX = {
        if (forward) slideDistance else -slideDistance
    }
) + fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
)


fun materialSharedAxisXOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = slideOutHorizontally(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    targetOffsetX = {
        if (forward) -slideDistance else slideDistance
    }
) + fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
)
fun materialSharedAxisY(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisYIn(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
) togetherWith materialSharedAxisYOut(
    forward = forward,
    slideDistance = slideDistance,
    durationMillis = durationMillis
)
fun materialSharedAxisYIn(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = slideInVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    initialOffsetY = {
        if (forward) slideDistance else -slideDistance
    }
) + fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
)

public fun materialSharedAxisYOut(
    forward: Boolean,
    slideDistance: Int,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = slideOutVertically(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    targetOffsetY = {
        if (forward) -slideDistance else slideDistance
    }
) + fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
)

fun materialSharedAxisZ(
    forward: Boolean,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ContentTransform = materialSharedAxisZIn(
    forward = forward,
    durationMillis = durationMillis
) togetherWith materialSharedAxisZOut(
    forward = forward,
    durationMillis = durationMillis
)



fun materialSharedAxisZIn(
    forward: Boolean,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = durationMillis.ForIncoming,
        delayMillis = durationMillis.ForOutgoing,
        easing = LinearOutSlowInEasing
    )
) + scaleIn(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    initialScale = if (forward) 0.8f else 1.1f
)

fun materialSharedAxisZOut(
    forward: Boolean,
    durationMillis: Int = MotionConstants.DefaultMotionDuration,
): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = durationMillis.ForOutgoing,
        delayMillis = 0,
        easing = FastOutLinearInEasing
    )
) + scaleOut(
    animationSpec = tween(
        durationMillis = durationMillis,
        easing = FastOutSlowInEasing
    ),
    targetScale = if (forward) 1.1f else 0.8f
)


private const val ProgressThreshold = 0.35f

private val Int.ForOutgoing: Int
    get() = (this * ProgressThreshold).toInt()

private val Int.ForIncoming: Int
    get() = this - this.ForOutgoing

