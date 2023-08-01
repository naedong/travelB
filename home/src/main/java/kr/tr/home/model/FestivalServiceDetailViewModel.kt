package kr.tr.home.model

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kr.tr.domain.model.item.GetFestivalKr
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.domain.repository.FestivalServiceDetailRepository
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-01
 * Time: 오후 1:09
 */
@HiltViewModel
class FestivalServiceDetailViewModel @Inject constructor(
    private val festivalServiceDetailRepository: FestivalServiceDetailRepository,
) : ViewModel() {

   suspend fun festivalServiveDetailViewModel(index: Int): Flow<GetFestivalKrItem?> {
      return flowOf(
          festivalServiceDetailRepository.getFestivalServiceDetail(index)
      )
   }

    
    
}

