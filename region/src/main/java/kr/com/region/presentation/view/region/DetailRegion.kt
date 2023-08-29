package kr.com.region.presentation.view.region

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.paging.compose.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import kr.com.region.presentation.model.ListDataModel
import kr.com.region.presentation.model.RegionViewModel
import kr.tr.commom.items.NavigationItem
import kr.tr.commom.theme.CustomMaterialTheme

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오전 11:06
 */
@Composable
fun DetailRegionScreen(navi: NavHostController) {
    var rememberSave by rememberSaveable { mutableStateOf(false) }
    var rememberSaveNumber by rememberSaveable { mutableStateOf(0) }
    val viewmodel = hiltViewModel<RegionViewModel>()
    val list = viewmodel.getToruCode.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier
            .padding(
                top = 3.dp,
            )
    ) {
        items(list) {
            it?.let { item ->
                Row(
                    modifier = Modifier
                        .padding(
                            start = 5.dp
                        )
                        .width(110.dp)
                        .height(50.dp)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(1.dp)
                        )
                        .clickable {
                            rememberSave = true
                            rememberSaveNumber = item.rnum
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(5.dp),
                        fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                        fontSize = 20.sp
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        contentDescription = "ArrowRight"
                    )
                }
            }
        }
        list.apply {
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
    if (rememberSave == true) {
        val viewmodel = hiltViewModel<ListDataModel>()
        val getAreaBasedListItem =
            viewmodel.onChangeStored(rememberSaveNumber).collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier
                .padding(top = 3.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            items(getAreaBasedListItem) { items ->
                Column(
                    modifier = Modifier
                        .padding(
                            start = 5.dp,
                            end = 5.dp,
                        )
                        .fillMaxWidth(0.75f)
                        .fillMaxHeight(0.9f)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(1.dp)
                        )
                        .clickable {
                            items?.let { area ->
                                area.sigungucode?.let { itemed ->
                                    viewmodel.setAreaBasedListItem(items)
                                }
                            }
                            items?.let {
                                navi.navigate(
                                    NavigationItem.region.route + "/${checkNotNull(it.sigungucode)}",
                                )
                            }
                        },
                )
                {
                    items?.title?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(5.dp),
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontSize = 18.sp
                        )
                    }
                    Row {
                        items?.addr1?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(
                                    bottom = 5.dp,
                                    top = 5.dp,
                                    start = 5.dp
                                ),
                                fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                                fontSize = 12.sp
                            )
                        }
                        items?.addr2?.let {
                            Text(
                                text = it,
                                modifier = Modifier.padding(
                                    bottom = 5.dp,
                                    top = 5.dp,
                                    start = 5.dp
                                ),
                                fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                                fontSize = 12.sp
                            )
                        }

                    }
                }
            }
            getAreaBasedListItem.apply {
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
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}


