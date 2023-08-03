package kr.tr.home.view.mainpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.tr.domain.model.item.GetAttractionKrItem
import kr.tr.home.model.AttrationServiceViewModel
import kr.tr.home.widget.HomeMediaCarousel
import kr.tr.home.widget.InfiniteLoopPager
import kr.tr.home.widget.getGradientDivider


@Composable
fun HomeMainPage(navController: NavHostController) {

    Column(modifier = Modifier
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


        Box(modifier = Modifier
            .height(150.dp)
            .zIndex(0f)
            .fillMaxWidth()
            .padding(top = 80.dp, bottom = 20.dp)
            .background(Color.White.copy(0f))
        )
    }

}

@Composable
fun HomeMainPageSlide(){
    val viewModel = hiltViewModel<AttrationServiceViewModel>()
    val list = viewModel.attrationServiceViewModel.collectAsLazyPagingItems()

    HomeMediaCarousel(list = list)
}


@Composable
fun HomeMainWatchedPlace() {

    InfiniteLoopPager(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(0.7f)
    )
}


@Composable
fun HomeMainAdsBanner() {

   Box(modifier = Modifier
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
    InfiniteLoopPager(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth(0.7f)
    )
}

