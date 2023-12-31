package kr.tr.commom.items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import kotlinx.coroutines.launch
import kr.tr.commom.font.maruburi_bold
import kr.tr.commom.items.indicator.TabIndicator
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.commom.theme.mainPurpleColor


@Composable
fun TabItem(
    colors: Color,
    isSelected: Boolean,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            Color.White
        } else {
            Color.Black.copy(alpha = 0.3f)
        }
    )

    val tabBallColor: Color by animateColorAsState(
        targetValue = when (colors) {

            CustomMaterialTheme.colorScheme.mySchemePrimary -> Color.Magenta
            CustomMaterialTheme.colorScheme.mySchemeSecondary -> Color.Green
            else -> Color.Blue
        },
    )

    ConstraintLayout(modifier = Modifier.fillMaxHeight())
    {
        val (textView, box, ball) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(textView)
                {
                    centerTo(parent)
                },
            text = text,
            color = tabTextColor,
            fontFamily = maruburi_bold,
            fontSize = 22.sp,

            )

        if (isSelected) {

            Box(modifier = Modifier
                .size(20.dp)
                .drawBehind {
                    drawRoundRect(
                        color = Color.White,
                        topLeft = Offset(x = -size.height / 2.7f, size.height / 2),
                        size = Size(width = size.width * 1.8f, height = size.height / 2),
                        cornerRadius = CornerRadius(y = 0f, x = 0f),
                        colorFilter = ColorFilter.colorMatrix(colorMatrix = ColorMatrix()),

                        )
                    drawRoundRect(
                        color = colors,
                        topLeft = Offset(x = -size.height, 0f),
                        size = size,
                        cornerRadius = CornerRadius(y = 40f, x = 40f)
                    )
                    drawRoundRect(
                        color = colors,
                        topLeft = Offset(x = size.height, 0f),
                        size = size,
                        cornerRadius = CornerRadius(y = 40f, x = 40f)
                    )
                }
                .clip(CircleShape)
                .background(Color.White)
                .constrainAs(box) {
                    top.linkTo(textView.bottom, margin = 8.dp)
                    start.linkTo(textView.start)
                    end.linkTo(textView.end)
                })



            Box(
                modifier = Modifier
                    .zIndex(1f)
                    .clip(CircleShape)

                    .background(tabBallColor)
                    .size(5.dp)
                    .constrainAs(ball) {
                        centerTo(box)
                    },
            )
        }


    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTab(
    tabItems: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 100.dp,
    pagerState: PagerState,
    backStackEntry: String = "0",

    ) {

    val coroutine = rememberCoroutineScope()


    LaunchedEffect(true) {
        coroutine.launch {
                pagerState.scrollToPage(backStackEntry.toInt())
        }
    }


    val colors by animateColorAsState(
        (when (pagerState.currentPage) {
            0 -> CustomMaterialTheme.colorScheme.mySchemePrimary
            1 -> CustomMaterialTheme.colorScheme.mySchemeSecondary
            else -> CustomMaterialTheme.colorScheme.mySchemeOnPrimary
        })
    )

    TabRow(selectedTabIndex = pagerState.currentPage,
        containerColor = Color.Cyan.copy(0.1f),
        contentColor = Color.Green.copy(0.1f),
        modifier = Modifier.zIndex(0f),

        // delete underLine
        divider = { },
        indicator = {
            TabIndicator(indicatorColor = colors)
        }) {
        tabItems.forEachIndexed { index, item ->
            Box(
                modifier = modifier
                    .clickable {
                        coroutine.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                    .zIndex(1f)
                    .fillMaxHeight(0.18f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center)
            {
                TabItem(
                    colors = colors,
                    isSelected = pagerState.targetPage == index,
                    tabWidth = tabWidth,
                    text = item,

                    )

            }
        }
    }
}







