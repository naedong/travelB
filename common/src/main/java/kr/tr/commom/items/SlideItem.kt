package kr.tr.commom.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 10:07
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SlideItem(
    pageCount : Int,
    autoScrollDuration: Long = 3000L,
) : PagerState {

    val pagerState : PagerState = rememberPagerState{ pageCount }
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if(isDragged.not()){
        with(pagerState){
            if(pageCount > 0) {
                var currentPageKey by remember {
                    mutableStateOf(0)
                }
                LaunchedEffect(key1 = currentPageKey){
                    launch {
                        delay(autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }
    return pagerState
}

