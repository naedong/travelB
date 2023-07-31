package kr.tr.commom.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-27
 * Time: 오후 2:56
 */

@Composable
fun ShimmerAnimation(isRowShimmer : Boolean = true)  {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        /*
         Specify animation positions,
         initial Values 0F means it
         starts from 0 position
        */
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(


            // Tween Animates between values over specified [durationMillis]
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    /*
      Create a gradient using the list of colors
      Use Linear Gradient for animating in any direction according to requirement
      start=specifies the position to start with in cartesian like system Offset(10f,10f) means x(10,0) , y(0,10)
      end = Animate the end position to give the shimmer effect using the transition created above
    */
    val brush = Brush.linearGradient(
        colors = listOf(Color.Blue.copy(0.4f), Color.DarkGray.copy(0.6f)),
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    if (isRowShimmer) {
        ShimmerRowItem(brush = brush)
    } else {
        ShimmerGridItem(brush = brush)
    }
}

@Composable
fun ShimmerRowItem(brush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = CustomMaterialTheme.colorScheme.mySchemeOnPrimary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 12.dp, top = 12.dp, end = 4.dp, bottom = 12.dp)
                    .background(brush = brush)
            )
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(start = 8.dp, top = 12.dp, end = 12.dp, bottom = 4.dp)
                        .background(brush = brush)
                )

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                        .padding(start = 8.dp, top = 8.dp, end = 12.dp, bottom = 8.dp)
                        .background(brush = brush)
                )
            }
        }
    }
}


@Composable
fun ShimmerGridItem(brush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.Cyan)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp)
                    .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 6.dp)
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 8.dp)
                    .background(brush = brush)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 8.dp)
                    .background(brush = brush)
            )
        }
    }
}