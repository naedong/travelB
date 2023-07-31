package kr.tr.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-06-29
 * Time: 오후 1:27
 */
@Composable
fun GradientDivider(
    modifer : Modifier = Modifier,
    height : Dp = 10.dp,
    colors : List<Color>,
    startOffset : Offset = Offset.Zero,
    endOffset : Offset = Offset.Infinite
) {
    Box(
        modifier = modifer
            .fillMaxWidth()
            .height(height)
            .background(
                Brush.horizontalGradient(
                    colors
                )
            ),
        )
}

@Composable
fun getGradientDivider() {
    GradientDivider(
        modifer = Modifier
            .fillMaxWidth(0.8f)
            .padding(top = 30.dp)
            .clip(shape = RoundedCornerShape(3.dp)),
        colors = listOf(
            CustomMaterialTheme.colorScheme.mySchemePrimary,
            CustomMaterialTheme.colorScheme.mySchemeOnPrimary
        ),
        height = 3.dp
    )
}