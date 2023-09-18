package kr.com.map.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kr.com.map.data.entity.SelectPosition
import kr.com.map.data.model.DocumentItemResult
import kr.com.map.util.ListMode
import kr.com.map.util.MapViewMode
import kr.tr.domain.model.item.MapLocationBasedItemItem
import kr.tr.domain.repository.MapLocationBasedInter
import kr.tr.domain.usecase.getBool
import kr.tr.domain.usecase.setPutBoolean
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode
import net.daum.mf.map.api.MapView.MapType
import javax.inject.Inject
import kotlin.math.absoluteValue

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 3:06
 */
@HiltViewModel
class MapViewModel @Inject constructor(
    private val setBool : setPutBoolean,
    private val getBool : getBool,
    private val mapLocation : MapLocationBasedInter,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var _documentResultEvent = MutableLiveData<DocumentItemResult>()
    val documentResultEvent: LiveData<DocumentItemResult> = _documentResultEvent
    var selectPositionEvent = MutableLiveData<SelectPosition>()
    val requireDocumentList get() = documentResultEvent.value!!.documentList


    val listModeLiveData = savedStateHandle.getLiveData(LIST_MODE, ListMode.LIST)
    val mapViewModeLiveData = savedStateHandle.getLiveData(MAP_VIEW_MODE, MapViewMode.DEFAULT)
    var mapViewMode: MapViewMode
        get() = mapViewModeLiveData.value!!
        set(mapViewMode) {
            if (mapViewMode.isNotDefault) {
                disableTrackingMode()
                listMode = ListMode.LIST
            }
            mapViewModeLiveData.value = mapViewMode
        }
    var listMode: ListMode
        get() = listModeLiveData.value!!
        set(listMode) {
            listModeLiveData.value = listMode
        }

    fun disableTrackingMode() {
        setTrackingMode(CurrentLocationTrackingMode.TrackingModeOff)
    }

    fun selectDocument(position: Int, selectedByMap: Boolean) {
        selectPositionEvent.value = SelectPosition(position, selectedByMap)
    }

    private val _useGPS = MutableLiveData<List<Double>>()
    var useGPS : LiveData<List<Double>> = _useGPS

    fun setUseGPS(lati: Double, long : Double){
        _useGPS.value = listOf(lati, long)
    }

    private val _Length = MutableLiveData<Float>()
    var length : LiveData<Float> = _Length

    private val _TrackingMode = MutableLiveData<CurrentLocationTrackingMode>()
    var trackingMode : LiveData<CurrentLocationTrackingMode> = _TrackingMode

    private val _MyMapType = MutableLiveData<MapType>()
    var myMapType : LiveData<MapType> = _MyMapType

    private val _TypeBackGround = MutableLiveData<Boolean>()
    var typeBackground : LiveData<Boolean> = _TypeBackGround

    private val _Markers = MutableLiveData<MapPOIItem>()
    val markers : LiveData<MapPOIItem> = _Markers

    fun setLength(set : Float){
        _Length.value = set
    }
    fun getMapLocation(contentTypeID : String = "39"): Flow<PagingData<MapLocationBasedItemItem>> {
         return mapLocation.getMapLocationBasedInter(
                contentTypeId = contentTypeID,
                mapY = useGPS.value?.get(1).toString(),
                mapX = useGPS.value?.get(0).toString(),
                radius = length.value?.absoluteValue.toString()
            ).cachedIn(viewModelScope)
    }



    fun setTrackingMode(set : CurrentLocationTrackingMode){
        _TrackingMode.value = set
    }
    fun setTypeBackGround(set : Boolean){
        _TypeBackGround.value = set
    }
    fun setMyMapType(mapType: MapType){
         when(mapType){
            MapType.Satellite -> { _MyMapType.value = MapType.Satellite }
            MapType.Hybrid -> { _MyMapType.value = MapType.Hybrid }
            else -> { _MyMapType.value = MapType.Standard }
        }
    }

    companion object {
        private const val MAP_VIEW_MODE = "MAP_VIEW_MODE"
        private const val LIST_MODE = "LIST_MODE"
        private const val TEMP_TRACKING_MODE = "TEMP_TRACKING_MODE"
    }
}

