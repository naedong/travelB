package kr.com.region.presentation.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.tr.domain.repository.TourismRepositoryInter
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오후 2:43
 */
@HiltViewModel
class RegionViewModel @Inject constructor(
    private val repository : TourismRepositoryInter,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val getToruCode = repository.invoke().cachedIn(viewModelScope)

}