package kr.tr.home.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-06-29
 * Time: 오후 2:26
 */
const val AUTO_PAGE_CHANGE_DELAY = 3000L

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfiniteLoopPager(
    modifier: Modifier = Modifier,
    list: List<Color> = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.Blue,
        Color.Magenta,
        Color.Cyan
    )
) {
    val pagerState = rememberPagerState()

    // 초기페이지 설정. 한번만 실행되기 원하니 key 는 Unit|true.
    LaunchedEffect(key1 = Unit) {
        // 최대한 많은 페이지 양쪽으로 보여주기 위함.
        var initialPage = Int.MAX_VALUE / 2

        // 초기페이지를 0으로 잡는다.
        while (initialPage % list.size != 0) {
            initialPage++
        }
        pagerState.scrollToPage(initialPage)
    }
//
//    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
//    if (isDragged.not()) {
//        with(pagerState) {
//            var currentPageKey by remember { mutableStateOf(0) }
//            LaunchedEffect(key1 = currentPageKey) {
//                launch {
//                    delay(timeMillis = AUTO_PAGE_CHANGE_DELAY)
//                    val nextPage = (currentPage + 1).mod(initialPage)
//                    animateScrollToPage(page = nextPage)
//                    currentPageKey = nextPage
//                }
//            }
//        }
//    }

    // 지정한 시간마다 auto scroll.
    // 유저의 스크롤해서 페이지가 바뀐경우 다시 실행시키고 싶기 때문에 key는 currentPage.
    LaunchedEffect(key1 = pagerState.currentPage) {
        launch {
            while (true) {
                delay(AUTO_PAGE_CHANGE_DELAY)
                // 페이지 바뀌었다고 애니메이션이 멈추면 어색하니 NonCancellable
                withContext(NonCancellable) {
                    // 일어날린 없지만 유저가 약 10억번 스크롤할지 몰라.. 하는 사람을 위해..
                    if (pagerState.currentPage + 1 in 0..Int.MAX_VALUE) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }
        }
    }



    Box(modifier = modifier
        .fillMaxHeight(),
     contentAlignment = Alignment.Center) {
        HorizontalPager(
            pageCount = Int.MAX_VALUE,
            modifier = Modifier.aspectRatio(16f / 9f)
                .clip(shape = RoundedCornerShape(10.dp)), // PageSize.Fill 상태에서 비율만 줘보기.
            state = pagerState,
            pageSpacing = 5.dp
        ) { index ->
            // index % (list.size) 나머지 값으로 인덱스 가져오기. 안전하게 getOrNull 처리.

//            CarouselItem()

            list.getOrNull(index % (list.size))?.let { color ->
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = color)
                )
            }
        }
        PagerIndicator(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = 16.dp, bottom = 16.dp),
            count = list.size,
            dotSize = 6.dp,
            spacedBy = 4.dp,
            currentPage = pagerState.currentPage % list.size,
            selectedColor = Color.White,
            unSelectedColor = Color.LightGray
        )
    }
}


@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    dotSize: Dp,
    spacedBy: Dp,
    currentPage: Int,
    selectedColor: Color,
    unSelectedColor: Color
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(spacedBy)) {
        (0 until count).forEach { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .background(
                        color = if (index == currentPage) {
                            selectedColor
                        } else {
                            unSelectedColor
                        },
                        shape = CircleShape
                    )
            )
        }
    }
}