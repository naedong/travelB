package kr.com.region.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kr.tr.domain.repository.TourismRepositoryInter
import kr.tr.domain.repository.getStationCodeRepositoryInter
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오후 2:19
 */
@HiltViewModel
class SubWayViewModel @Inject constructor(
    private val repository : getStationCodeRepositoryInter,
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    val stationCode = repository.invoke().cachedIn(viewModelScope)

    private val _booleanSave = MutableLiveData("")
    val booleanSave : LiveData<String> = _booleanSave

    fun setBooleanSaveInternal(str: String) {
        _booleanSave.value = str
    }


}