package kr.tr.home.view.mainpage

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import kr.tr.home.widget.SlideItem
import kr.tr.home.model.AttrationServiceViewModel
import kr.tr.home.model.FestivalServiceViewModel
import kr.tr.home.widget.CarouselItem
import kr.tr.home.widget.FestivalCarouselItem
import kr.tr.home.widget.HomeMediaCarousel
import kr.tr.home.widget.InfiniteLoopPager
import kr.tr.home.widget.InfoItem
import kr.tr.home.widget.getGradientDivider


@Composable
fun HomeMainPage(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        HomeMainPageSlide()

        getGradientDivider()

//        HomeMainAdsBanner()

//        getGradientDivider()

        HomeMainPeopleChoice()

        getGradientDivider()

        HomeMainWatchedPlace()


        Box(
            modifier = Modifier
                .height(150.dp)
                .zIndex(0f)
                .fillMaxWidth()
                .padding(top = 80.dp, bottom = 20.dp)
                .background(Color.White)
        )
    }

}

@Composable
fun HomeMainPageSlide() {
    val viewModel = hiltViewModel<AttrationServiceViewModel>()
    val list = viewModel.attrationServiceViewModel.collectAsLazyPagingItems()

    HomeMediaCarousel(list = list)
}

@Composable
fun HomeMainWatchedPlace() {
    InfoItem()

}


@Composable
fun HomeMainAdsBanner() {

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .zIndex(1f)
            .padding(top = 20.dp)
    ) {
        InfiniteLoopPager(
            modifier = Modifier
        )
    }

}

@Composable
fun HomeMainPeopleChoice() {
    InfoItem()
}

