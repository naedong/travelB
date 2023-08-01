package kr.tr.home.view.schedule

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.home.model.FestivalServiceDetailViewModel


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오후 3:17
 */

@Composable
fun ScheduleDetailPage(navController: NavHostController, index: Int) {
    val viewModel = hiltViewModel<FestivalServiceDetailViewModel>()

//
//    var resultItem by remember { mutableStateOf<GetFestivalKrItem?>(null) }
//
//    LaunchedEffect(Unit){
//         viewModel.festivalServiveDetailViewModel(index).collect(){
//             resultItem = it
//
//
//             Log.e("mainUI","it 값 확인")
//             Log.e("mainUI","$it")
//             Log.e("mainUI","${it?.title}")
//         }
//
//    }

    val saveableStateHolder = rememberSaveableStateHolder()



    var setResultItem by rememberSaveable { mutableStateOf<GetFestivalKrItem?>(null) }


    // 코루틴 블록 내에서 festivalServiveDetailViewModel 호출
    // 예시: viewModelScope.launch
    LaunchedEffect(Unit){
            val result = viewModel.festivalServiveDetailViewModel(index)
            result.collect(){
                Log.e("limit Line", "$it")
                setResultItem == it
            }
    }
    Log.e("next Line", "다음 라인")
    Log.e("next Line", "$setResultItem")

    LazyColumn {
        item {

                Column {
                    Text(text = "데이터 확인")
                    Text(text = "전달 값 확인 ${index}")
                    Text(text = "${setResultItem}")
                    Text("viewmodel 작동확인 ${setResultItem})")

            }
        }
    }


}


