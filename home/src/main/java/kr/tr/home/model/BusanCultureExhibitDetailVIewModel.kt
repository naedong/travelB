package kr.tr.home.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.tr.domain.repository.BusanCultureExhibitDetailRepository
import javax.inject.Inject

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오후 1:35
 */

@HiltViewModel
class BusanCultureExhibitDetailViewModel @Inject constructor(
    private val busanExhibitDetail : BusanCultureExhibitDetailRepository
) : ViewModel() {
    val busanCultureExhibitDetailList = busanExhibitDetail.getBusanCultureExhibitDetailPagingItem().cachedIn(viewModelScope)
}