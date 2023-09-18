package kr.tr.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import kr.tr.commom.R
import kr.tr.commom.items.CustomTab
import kr.tr.commom.utill.MaterialMotion
import kr.tr.commom.utill.materialSharedAxisX
import kr.tr.commom.utill.rememberSlideDistance
import kr.tr.home.view.mainpage.HomeMainPageScreen
import kr.tr.home.view.schedule.HomeSchedulePageScreen
import kr.tr.home.view.ticket.HomeTicketingPageScreen

@Composable
fun HomeScreenRoute(navController: NavHostController, backStackEntry: String) {

    HomeScreen(navController, backStackEntry)
}

@Composable
fun HomeScreen(navController: NavHostController, backStackEntry: String) {

    HomeScreenSetting(navController, backStackEntry)

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenSetting(navController: NavHostController, backStackEntry: String) {
    val forward = remember { mutableStateOf(false) }
    val tabItem = listOf(
        stringResource(id = R.string.Main_FirstTapItem),
        stringResource(id = R.string.Main_SecondTapItem),
    )
    val pagerState = rememberPagerState{ tabItem.size }
    Column(
        modifier = Modifier
            .zIndex(0f)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
            CustomTab(
                tabItems = tabItem, pagerState = pagerState, backStackEntry = backStackEntry
            )
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
            pageSpacing = 0.dp,
            userScrollEnabled = true,
            reverseLayout = false,
            contentPadding = PaddingValues(0.dp),
            beyondBoundsPageCount = 0,
            pageSize = PageSize.Fill,
            flingBehavior = PagerDefaults.flingBehavior(state = pagerState),
            key = null,
            pageNestedScrollConnection = remember(pagerState) {
                PagerDefaults.pageNestedScrollConnection(pagerState, Orientation.Horizontal)
            }) {index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val slideDistance = rememberSlideDistance()
                MaterialMotion(targetState = index, transitionSpec = {
                       materialSharedAxisX(forward = forward.value, slideDistance = slideDistance)
                }
                ) {
                    when (it) {
                        0 -> {
                            HomeSchedulePageScreen(navController)
                        }
                        1 -> {
                            HomeMainPageScreen(navController)
                        }
                        else -> {
                            HomeTicketingPageScreen(navController)
                        }
                    }
                }
            }
        }
    }
}





