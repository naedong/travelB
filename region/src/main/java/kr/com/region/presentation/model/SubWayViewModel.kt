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
import kr.tr.domain.model.item.MapLocationBasedItemItem
import kr.tr.domain.repository.MapLocationBasedInter
import kr.tr.domain.repository.getStationCodeRepositoryInter
import javax.inject.Inject
import kotlin.math.absoluteValue

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오후 2:19
 */
@HiltViewModel
class SubWayViewModel @Inject constructor(
    private val mapLocation : MapLocationBasedInter,
    private val repository : getStationCodeRepositoryInter,
    private val savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
    val stationCode = repository.invoke().cachedIn(viewModelScope)

    private val _booleanSave = MutableLiveData("")
    val booleanSave : LiveData<String> = _booleanSave

    fun setBooleanSaveInternal(str: String) {
        _booleanSave.value = str
    }

    fun getSubWayMapLocation(contentTypeID : String = "39",
                            mapY : String,
                             mapX : String,
                             radius : String = "1000"
    ): Flow<PagingData<MapLocationBasedItemItem>> {
        return mapLocation.getMapLocationBasedInter(
            contentTypeId = contentTypeID,
            mapY = mapY,
            mapX = mapX,
            radius = radius
        ).cachedIn(viewModelScope)
    }

}