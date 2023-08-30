package kr.com.region.presentation.view.subway

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import kr.com.region.presentation.model.SubWayViewModel
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오전 11:05
 */
@Composable
fun SubWayScreen() {
    ListViewModel()

}

@Composable
fun ListViewModel() {
    val viewModel = hiltViewModel<SubWayViewModel>()
    val viewModelItem = viewModel.stationCode.collectAsLazyPagingItems()
    val getSaveString: String by viewModel.booleanSave.observeAsState("")

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(
                top = 3.dp,
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        items(viewModelItem.itemCount) { index ->
            viewModelItem[index]?.routeName?.let {
                if (index == 0) itemRow(it, viewModel)
                else if (!viewModelItem[index - 1]?.routeName.equals(it)) itemRow(it, viewModel)
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(top = 3.dp)
            .fillMaxWidth(0.9f)
            .fillMaxHeight()
    ) {
        if (getSaveString.isNotEmpty()) {
            items(viewModelItem) { item ->
                if (getSaveString.equals(item?.routeName)) {
                    item?.stationName?.let {
                        StationNameItem(str = it)
                    }
                }
            }
            item {
                Text(text = ""
                    , modifier = Modifier
                        .height(140.dp)
                        .zIndex(0f)
                        .fillMaxWidth()
                        .padding(top = 3.dp, bottom = 100.dp)
                        .background(Color.White)
                )

            }
        }
        viewModelItem.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val errorMessage = (loadState.refresh as LoadState.Error).error.message
                    item {
                        Text(
                            text = "$errorMessage",
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontSize = 20.sp
                        )
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    val errorMessage = (loadState.append as LoadState.Error).error.message
                    item {
                        Text(
                            text = "$errorMessage",
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontSize = 20.sp
                        )

                    }
                }
            }
        }
    }
}

@Composable
fun StationNameItem(str: String) {

    Row(
        modifier = Modifier
            .padding(
                start = 15.dp,
                end = 50.dp
            )
            .fillMaxWidth(0.7f)
            .height(50.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(1.dp)
            )
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = str,
            modifier = Modifier.padding(start = 15.dp),
            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
            fontSize = 15.sp
        )

    }

}

@Composable
fun itemRow(str: String, rememberSave: SubWayViewModel) {

    Row(
        modifier = Modifier
            .padding(
                start = 5.dp,
                end = 5.dp
            )
            .width(150.dp)
            .height(50.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(1.dp)
            )
            .clickable {
                rememberSave.setBooleanSaveInternal(str)
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = "$str",
            modifier = Modifier.padding(5.dp),
            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
            fontSize = 15.sp
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp),
            contentDescription = "ArrowRight"
        )
    }

}

