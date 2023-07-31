package kr.tr.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-06-29
 * Time: 오후 4:18
 */
@Composable
fun CarouselItem() {
    Box {
        Box(
            
//            model = item.backdropPath.toFullPosterUrl(),
//            contentDescription = null,
//            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
//            error = painterResource(id = R.drawable.ic_load_error),
//            contentScale = ContentScale.FillBounds,
            modifier = Modifier
//                .height(dimensionResource(id = R.dimen.home_grid_poster_height))
                .fillMaxWidth()
        )
        val gradient =
            Brush.verticalGradient(listOf(Color.Transparent, Color.Blue.copy(alpha = 0.4f)))

        Text(
            text = "test",
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(
//                    horizontal = dimensionResource(id = R.dimen.normal_padding),
//                    vertical = dimensionResource(id = R.dimen.small_padding)
                )
        )
    }
}