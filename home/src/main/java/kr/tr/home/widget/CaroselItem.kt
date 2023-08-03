package kr.tr.home.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.util.lerp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.domain.model.item.GetAttractionKrItem
import kr.tr.domain.util.Constants
import kotlin.math.absoluteValue

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-06-29
 * Time: 오후 4:18
 */

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeMediaCarousel(
    list: LazyPagingItems<GetAttractionKrItem>,
    totalItemsToShow: Int = 10,
    carouselLabel: String = "",
    autoScrollDuration: Long = 3000L,
) {
    val pageCount = list.itemCount.coerceAtMost(totalItemsToShow)
    val pagerState: PagerState = rememberPagerState(pageCount)
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 32.dp
                ),
                pageSpacing =  8.dp,
                pageCount = pageCount
            ) { page: Int ->
                val item: GetAttractionKrItem? = list[page]
                item?.let {
                    Card(
                        onClick = {  },
                        modifier = Modifier
                            .carouselTransition(page, pagerState)
                    ) {
                        CarouselItem(it)
                    }
                }
            }
        }

        if (carouselLabel.isNotBlank()) {
            Text(text = carouselLabel, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun CarouselItem(item: GetAttractionKrItem) {
    Box {
        AsyncImage(
            model = item.mainImgNormal,
            contentDescription = null,
//            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
//            error = painterResource(id = R.drawable.ic_load_error),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth()
        )
        val gradient =
            Brush.verticalGradient(listOf(Color.Transparent, CustomMaterialTheme.colorScheme.mySchemeSecondary))

        Text(
            text = item.title,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation = lerp(
            start = 0.8f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
        alpha = transformation
        scaleY = transformation
    }
