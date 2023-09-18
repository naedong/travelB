package kr.tr.home.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
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
  val festivalServiceModel = festivalServiceRepository.getFestivalServiceItem().cachedIn(viewModelScope)
}