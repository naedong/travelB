package kr.com.region.presentation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import kr.com.region.item.TabItem
import kr.com.region.presentation.view.region.DetailRegionScreen
import kr.com.region.presentation.view.subway.SubWayScreen
import kr.tr.commom.items.CustomTab

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오후 2:43
 */
@Composable
fun RegionScreen(navController: NavHostController) {

    RegionTab(navController)

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RegionTab(navController: NavHostController) {
    val tabItem = listOf(TabItem().region, TabItem().subWay)

    val pagerState = rememberPagerState {
        tabItem.size
    }


    Column(
        modifier = Modifier
            .zIndex(0f)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        CustomTab(tabItem, pagerState = pagerState)

        regionPager(
            navi = navController,
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollerEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
        )
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun regionPager(
    modifier: Modifier = Modifier,
    state: PagerState,
    pageSpacing: Dp,
    userScrollerEnabled: Boolean,
    reverseLayout: Boolean,
    contentPadding: PaddingValues,
    beyondBoundsPageCount: Int,
    pageSize: PageSize = PageSize.Fill,
    flingBehavior: SnapFlingBehavior = PagerDefaults.flingBehavior(state),
    key: String = "",
    navi: NavHostController,
){

    HorizontalPager(
        modifier = modifier,
        state = state,
        pageSpacing = pageSpacing,
        reverseLayout = reverseLayout,
        contentPadding = contentPadding,
        beyondBoundsPageCount = beyondBoundsPageCount,
        userScrollEnabled = userScrollerEnabled,
        pageSize = pageSize,
        flingBehavior = flingBehavior,
        key = null,
        pageNestedScrollConnection = remember(state){
            PagerDefaults.pageNestedScrollConnection(state, Orientation.Horizontal)
        }
    ) {
        when(it){
            0 -> DetailRegionScreen(navi)
            else -> SubWayScreen()
        }

    }

}

