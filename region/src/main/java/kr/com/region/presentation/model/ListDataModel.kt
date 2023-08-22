package kr.com.region.presentation.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.repository.AreaBasedItemRepositoryInter
import kr.tr.domain.repository.TourismRepositoryInter
import javax.inject.Inject


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오전 11:52
 */
@HiltViewModel
class ListDataModel @Inject constructor(
    private val repository: AreaBasedItemRepositoryInter,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    fun onChangeStored(idx: Int): Flow<PagingData<AreaBasedListItem>> {
        return repository.getAreaBaseItemInter(idx).cachedIn(viewModelScope)
    }


}
