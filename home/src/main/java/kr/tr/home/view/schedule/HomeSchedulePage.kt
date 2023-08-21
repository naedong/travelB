package kr.tr.home.view.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import dagger.hilt.android.ViewModelLifecycle
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScheudleItem(navController: NavHostController) {
    val viewModel = hiltViewModel<FestivalServiceViewModel>()

    val festivalServiceModel =
        viewModel.festivalServiceModel.collectAsLazyPagingItems()


    val focusManager = LocalFocusManager.current
    var showDetail by rememberSaveable { mutableStateOf(false) }

    var active by rememberSaveable {
        mutableStateOf(false)
    }
    var text by rememberSaveable {
        mutableStateOf("")
    }
//    var setListItem by rememberSaveable {
//       mutableStateListOf(
//       )
//    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        SearchBar(
            modifier = Modifier
                .padding(3.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() }
                    )
                },
            query = text,
            onQueryChange = { text = it },
            onSearch = { active },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text("검색") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
        )
        {
            repeat(4) { idx ->
                val resultText = "Suggestion $idx"
                ListItem(
                    headlineContent = { Text(resultText) },
                    supportingContent = { Text("Additional info") },
                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
                    modifier = Modifier
                        .clickable {
                            text = resultText
                            active = false
                        }
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }

        LazyColumn {


            festivalServiceModel.let { use ->

                items(use) { item ->
                    item?.let { it ->
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

//                            androidx.compose.material3.Text(text = "Loading more data...")
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


//                            Log.e("TrevalB",  "Error: $errorMessage")
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

//                            Log.e("TrevalB",  "Error: $errorMessage")
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

//        LazyColumn(
//            contentPadding = PaddingValues(start = 16.dp, top = 72.dp, end = 16.dp, bottom = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            val list = List(10) { "Text $it" }
//            items(count = list.size) {
//                Text(list[it], Modifier.fillMaxWidth().padding(horizontal = 16.dp))
//            }
//        }
    }


}


@Composable
fun <T : Any> Navigation(
    currentScreen: T,
    modifier: Modifier = Modifier,
    content: @Composable (T) -> Unit
) {
    // create SaveableStateHolder.
    val saveableStateHolder = rememberSaveableStateHolder()
    Box(modifier) {
        // Wrap the content representing the `currentScreen` inside `SaveableStateProvider`.
        // Here you can also add a screen switch animation like Crossfade where during the
        // animation multiple screens will be displayed at the same time.
        saveableStateHolder.SaveableStateProvider(currentScreen) {
            content(currentScreen)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {


}
