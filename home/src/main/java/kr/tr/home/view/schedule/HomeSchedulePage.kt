package kr.tr.home.view.schedule

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kr.tr.home.item.CardItem
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



    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,


    ) {

        festivalServiceModel.let { use ->

            items(use) { item ->
                item?.let { it ->


                    CardItem(
                        classPath = it,
                        nav = navController
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
                    // 보기에 좋지 않아 잠시 주석 처리
                    loadState.append is LoadState.Loading -> {
                        item {

//                            androidx.compose.material3.Text(text = "Loading more data...")
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .zIndex(0f)
                                    .fillMaxWidth()
                                    .padding(top = 80.dp, bottom = 20.dp)
                                    .background(Color.White)
                            )
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val errorMessage = (loadState.refresh as LoadState.Error).error.message
                        item {


//                            Log.e("TrevalB",  "Error: $errorMessage")
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .zIndex(0f)
                                    .fillMaxWidth()
                                    .padding(top = 80.dp, bottom = 20.dp)
                                    .background(Color.White)
                            )
                        }

                    }

                    loadState.append is LoadState.Error -> {
                        val errorMessage = (loadState.append as LoadState.Error).error.message
                        item {

//                            Log.e("TrevalB",  "Error: $errorMessage")
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .zIndex(0f)
                                    .fillMaxWidth()
                                    .padding(top = 80.dp, bottom = 20.dp)
                                    .background(Color.White)
                            )

                        }
                    }
                }
            }

        }


    }

}



