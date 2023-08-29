package kr.tr.home.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.domain.model.item.GetAttractionKrItem
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.home.model.FestivalServiceViewModel
import kotlin.math.absoluteValue

import androidx.compose.ui.util.lerp
import kr.tr.domain.model.item.GetAttractionKrItemWrapper

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
    savedState: MutableState<GetAttractionKrItemWrapper?>,
) {

    val pageCount = list.itemCount.coerceAtMost(totalItemsToShow)
    val pagerState: PagerState = rememberPagerState { pageCount }
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()


    var currentPageKey by remember { mutableStateOf(0) }


    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {

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
                modifier = Modifier,
                state = pagerState,
                pageSpacing = 8.dp,
                userScrollEnabled = true,
                reverseLayout = false,
                contentPadding = PaddingValues(
                    horizontal = 32.dp
                ),
            ) { page ->
                val item: GetAttractionKrItem? = list[page]
                item?.let {
                    Card(
                        onClick = { },
                        modifier = Modifier
                            .carouselTransition(page, pagerState)
                    ) {
                        CarouselItem(it)
                    }
                }
            }
        }
        if (carouselLabel.isNotBlank()) {
            Text(
                text = carouselLabel,
                style = MaterialTheme.typography.labelSmall
            )
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
            Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    CustomMaterialTheme.colorScheme.mySchemeSecondary
                )
            )

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


@Composable
fun FestivalCarouselItem(item: GetFestivalKrItem) {
    Box {
        AsyncImage(
            model = item.mainImgNormal,
            contentDescription = null,
//            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
//            error = painterResource(id = R.drawable.ic_load_error),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
        )
        val gradient =
            Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    CustomMaterialTheme.colorScheme.mySchemeSecondary
                )
            )
        item.title?.let {
            Text(
                text = it,
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
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SlideItem(
    autoScrollDuration: Long = 3000L,
) {

    val viewModel = hiltViewModel<FestivalServiceViewModel>()
//
//    val currentPage = viewModel.getCurrentPage()
//
//    LaunchedEffect(key1 = currentPage){
//        viewModel.saveCurrentPage(currentPage)
//    }
//

    val list = viewModel.festivalServiceModel.collectAsLazyPagingItems()
    val totalItems = 10
    val pageCount = list.itemCount.coerceAtMost(totalItems)
    val pagerState: PagerState = rememberPagerState { pageCount }
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {

            }
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp),

        ) {
        Text(
            text = "축제 정보",
            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(1f)
        ) {


            Box {

                list.itemCount.coerceAtMost(10)
                LazyRow(
                    modifier = Modifier
                        .fillMaxHeight(0.7f),
                    flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
                    userScrollEnabled = true,
                    reverseLayout = false,
                    contentPadding = PaddingValues(
                        15.dp
                    ),
                ) {
                    items(list.itemCount) {
                        list[it].let { listItem ->
                            Card(
                                onClick = { },
                                modifier = Modifier
                                    .padding(
                                        start = 15.dp,
                                        end = 15.dp,
                                    )
                                    .carouselTransition(it, pagerState)
                            ) {
                                listItem?.let { item: GetFestivalKrItem ->
                                    FestivalCarouselItem(item)
                                }

                            }
                        }

                    }

//                        list[it].let { listItem ->
//                            Card(
//                                onClick = { },
//                                modifier = Modifier
////                                    .carouselTransition(it, pagerState)
//                            ) {
//                                listItem?.let { item: GetFestivalKrItem ->
//                                    FestivalCarouselItem(item)
//                                }
//
//                            }
//                        }

                }

            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

        val transformation = lerp(
            start = 0.9f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
        alpha = transformation
        scaleY = transformation
    }


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun InfoItem(
) {

    val viewModel = hiltViewModel<FestivalServiceViewModel>()

    val list = viewModel.festivalServiceModel.collectAsLazyPagingItems()

    val totalItems = 10
    val pageCount = list.itemCount.coerceAtMost(totalItems)
    val pagerState: PagerState = rememberPagerState { pageCount }

    Column(
        modifier = Modifier
    ) {
        Text(
            text = "축제 정보",
            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(start = 40.dp, top = 5.dp)
        )


        LazyRow(
            modifier = Modifier,
//            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(
                15.dp
            ),
        ) {
            items(list.itemCount) {
                list[it]?.let { listItem ->

                    OutlinedCard(
                        modifier = Modifier
                            .padding(5.dp)
                            .width(150.dp)
                            .height(150.dp),
                        border = BorderStroke(width = 1.dp, color = Color.Gray),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        ),

                        onClick = {},
                    ) {
                        Column {
                            AsyncImage(
                                model = listItem.mainImgNormal,
                                contentDescription = null,
//            placeholder = painterResource(id = R.drawable.ic_load_placeholder),
//            error = painterResource(id = R.drawable.ic_load_error),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 12.dp))
                                    .height(120.dp)

                            )
                            listItem.title?.let {
                                Text(
                                    modifier = Modifier.padding(
                                        start = 5.dp,
                                        top = 10.dp

                                    ),
                                    text = it,
                                    fontSize = 8.sp,
                                    maxLines = 1 ,
                                    textAlign = TextAlign.Left,
                                    overflow = TextOverflow.Visible,
                                    fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black

                                )
                            }

                        }

                    }

                }
            }

        }
    }
}



