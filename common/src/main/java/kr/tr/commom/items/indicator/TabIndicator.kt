package kr.tr.commom.items.indicator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kr.tr.commom.theme.myWindowView

@Composable
fun TabIndicator(
    indicatorColor: Color,
) {
    if(indicatorColor != null){
        myWindowView(colors = indicatorColor)
    }
  Box(
        modifier = Modifier

            .fillMaxHeight(0.18f)
            .fillMaxWidth()
//            .offset(
//                x = indicatorOffset,
//            )
            .clip(
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
            )
            .background(
                color = indicatorColor,
            ),
    )
}
