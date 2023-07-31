package kr.tr.home.view.schedule

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kr.tr.home.item.SchedulePageItem
import kr.tr.home.model.FestivalServiceViewModel


@Composable
fun HomeSchedulePage(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    ) {

        MainScheudleItem(navController)


    }

}

@Composable
fun MainScheudleItem(navController: NavHostController) {
    val viewModel = hiltViewModel<FestivalServiceViewModel>()

    val festivalServiceModel =
        viewModel.festivalServiceModel.collectAsLazyPagingItems()

//      var nav = rememberNavController()

    LazyColumn {



        festivalServiceModel.let { use ->

            items(use) { item ->
                item?.let { it ->

                    SchedulePageItem(
                        nav = navController,
                        imagePath = it.mainImgNormal,
                        gugunNm = it.gugunNm,
                        usageAmount = it.usageAmount,
                        usageDayWeekAndTime = it.usageDayWeekAndTime,
                        mainPlace = it.mainPlace,
                        place = it.place,
                        subTitle = it.subtitle,
                        usageDay = it.usageDay
                    )
                }

            }
            festivalServiceModel.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            // refresh 올 위치
                            CircularProgressIndicator()

                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {

                            androidx.compose.material3.Text(text = "Loading more data...")

                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = (loadState.refresh as LoadState.Error).error.message
                        item {


                            Log.e("TrevalB",  "Error: $errorMessage")

                        }

                    }

                    loadState.append is LoadState.Error -> {
                        val errorMessage = (loadState.append as LoadState.Error).error.message
                        item {

                            Log.e("TrevalB",  "Error: $errorMessage")


                        }
                    }
                }
            }
        }

    }
}



