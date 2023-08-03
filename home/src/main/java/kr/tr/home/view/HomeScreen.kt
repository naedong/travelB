
package kr.tr.home.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import kr.tr.commom.items.TabItem
import kr.tr.commom.items.indicator.TabIndicator
import kr.tr.commom.theme.CustomMaterialTheme
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



    val pagerState = rememberPagerState()

    val tabItem = listOf(
        "일정",
        "메인",
        "예매"
    )


    Column(
        modifier = Modifier
            .zIndex(0f)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        CustomTab(
            tabItems = tabItem, pagerState = pagerState, backStackEntry = backStackEntry
        )
        HorizontalPager(pageCount = tabItem.size, state = pagerState
            ) { page ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()


            ) {
                when(page){
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomTab(
    tabItems: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 100.dp,
    pagerState: PagerState,
    backStackEntry: String,

    ) {

    val coroutine = rememberCoroutineScope()




    LaunchedEffect(true) {
        coroutine.launch {
            pagerState.scrollToPage(backStackEntry.toInt())
        }
    }



    val colors by animateColorAsState(
        (when (pagerState.currentPage) {
            0 -> CustomMaterialTheme.colorScheme.mySchemePrimary
            1 -> CustomMaterialTheme.colorScheme.mySchemeSecondary
            else -> CustomMaterialTheme.colorScheme.mySchemeOnPrimary
        })
    )

    TabRow(selectedTabIndex = pagerState.currentPage,


        // delete underLine
        divider = {    },
            indicator = {
        TabIndicator(indicatorColor = colors)
    }) {
        tabItems.forEachIndexed { index, item ->
            Box(
                modifier = modifier
                    .clickable {
                        coroutine.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                    .zIndex(1f)
                    .fillMaxHeight(0.18f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                TabItem(
                    colors = colors,
                    isSelected = pagerState.targetPage == index,
                    tabWidth = tabWidth,
                    text = item,

                )
            }
        }

    }

}






