package kr.com.region.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.repository.AreaBasedItemRepositoryInter
import kr.tr.domain.usecase.getAreaBasedListItemDataStoreUseCase
import kr.tr.domain.usecase.setAreaBasedListItemDataStoreUseCase
import javax.inject.Inject


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오전 11:52
 */

@HiltViewModel
class ListDataModel @Inject constructor(
    private val getListUseCase : getAreaBasedListItemDataStoreUseCase,
    private val setListUseCase : setAreaBasedListItemDataStoreUseCase,
    private val repository: AreaBasedItemRepositoryInter,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _changeIndex = MutableLiveData(listOf(""))
    val changeIndex : LiveData<List<String>> = _changeIndex

    fun setAreaBasedListItem(area : AreaBasedListItem){
        setDataStore(area)
    }

    val getAreaBasedListItem : Flow<AreaBasedListItem> = getListUseCase.invoke()


    private fun setDataStore(area: AreaBasedListItem) = viewModelScope.launch {
            setListUseCase.excute(area)
    }

    fun onChangeStored(idx: Int): Flow<PagingData<AreaBasedListItem>> {
        return repository.getAreaBaseItemInter(idx).cachedIn(viewModelScope)
    }


}
