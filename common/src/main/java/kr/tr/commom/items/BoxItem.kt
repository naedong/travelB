package kr.tr.commom.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-01
 * Time: 오전 10:27
 */
@Composable
fun <T, B> BoxItem( t : T, b : B) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(
            horizontal = 40.dp,
            vertical = 6.dp
        )
        .clip(RoundedCornerShape(6.dp))
        .border(
            color = Color.Black,
            width = 1.dp,
            shape = RoundedCornerShape(6.dp)
        )
        .background(Color.White),

        ) {
                PageItem(t, b)
    }
}

@Composable
fun  PageItem(t: Any?, b: Any?) {

}
