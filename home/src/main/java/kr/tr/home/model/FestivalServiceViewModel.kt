package kr.tr.home.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.domain.repository.FestivalServiceRepository
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오전 10:38
 */
@HiltViewModel
class FestivalServiceViewModel @Inject constructor(
   private  val savedStateHandle: SavedStateHandle,
    private val festivalServiceRepository : FestivalServiceRepository
) : ViewModel() {

//    // Paging 데이터를 저장할 LiveData
//    private var pagingData: Flow<PagingData<GetFestivalKrItem>>? = null
//
//    // 현재 페이지 번호를 저장할 키
//    private val currentPageKey = "currentPage"
//
//    // 현재 페이지를 저장하는 함수
//    fun saveCurrentPage(pageNumber: Int) {
//        savedStateHandle.set(currentPageKey, pageNumber)
//    }
//
//    // 현재 페이지를 가져오는 함수
//    fun getCurrentPage(): Int {
//        return savedStateHandle.get(currentPageKey) ?: 1 // 기본값은 1
//    }
//
//    fun getPagingData() : Flow<PagingData<GetFestivalKrItem>>{
//        if(pagingData == null){
//            return festivalServiceRepository.getFestivalServiceItem().cachedIn(viewModelScope)
//        }
//        return (pagingData ?: null) as Flow<PagingData<GetFestivalKrItem>>
//    }

  val festivalServiceModel = festivalServiceRepository.getFestivalServiceItem().cachedIn(viewModelScope)




}