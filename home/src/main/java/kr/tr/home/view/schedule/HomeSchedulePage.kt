package kr.tr.home.view.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kr.tr.commom.utill.DayPreProcessing
import kr.tr.home.item.CardItem
import kr.tr.home.model.FestivalServiceViewModel


@Composable
fun HomeSchedulePage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        MainScheudleItem(navController)
    }

}

@Composable
fun MainScheudleItem(navController: NavHostController) {
    val viewModel = hiltViewModel<FestivalServiceViewModel>()

    val festivalServiceModel =
        viewModel.festivalServiceModel.collectAsLazyPagingItems()

    var showDetail by rememberSaveable { mutableStateOf(false) }


    LazyColumn {

        festivalServiceModel.let { use ->

            items(use) { item ->
                item?.let {
                    it.usageDayWeekAndTime?.let { day ->
                        if (DayPreProcessing(day)) null
                        else {
                            CardItem(
                                classPath = it,
                                nav = navController
                            )
                        }
                    }

                }

            }

            festivalServiceModel.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            // refresh 올 위치
                            if (!showDetail) {
                                CircularProgressIndicator()
                                showDetail = true
                            }
                        }
                    }
                    // 보기에 좋지 않아 잠시 주석 처리
                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .height(140.dp)
                                    .zIndex(0f)
                                    .fillMaxWidth()
                                    .padding(top = 80.dp, bottom = 20.dp)
                                    .background(Color.White.copy(0f))
                            )
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = (loadState.refresh as LoadState.Error).error.message
                        item {
                            Box(
                                modifier = Modifier
                                    .height(140.dp)
                                    .zIndex(0f)
                                    .fillMaxWidth()
                                    .padding(top = 80.dp, bottom = 20.dp)
                                    .background(Color.White.copy(0f))
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val errorMessage = (loadState.append as LoadState.Error).error.message
                        item {
                            Box(
                                modifier = Modifier
                                    .height(140.dp)
                                    .zIndex(0f)
                                    .fillMaxWidth()
                                    .padding(top = 80.dp, bottom = 20.dp)
                                    .background(Color.White.copy(0f))
                            )
                        }
                    }
                }
            }
        }
    }
}



