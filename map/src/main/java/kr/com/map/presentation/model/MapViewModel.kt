package kr.com.map.presentation.model

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kr.com.map.data.entity.DocumentResult
import kr.com.map.data.entity.SelectPosition
import kr.com.map.data.model.Document
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import kr.tr.domain.usecase.getBool
import kr.tr.domain.usecase.getMapDataTrackingUseCase
import kr.tr.domain.usecase.setMapDataTrackingUseCase
import kr.tr.domain.usecase.setPutBoolean
import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode
import net.daum.mf.map.api.MapView.MapType
import javax.inject.Inject

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
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _MyMapType = MutableLiveData<MapType>()
    var myMapType : LiveData<MapType> = _MyMapType

    private val _TypeBackGround = MutableLiveData<Boolean>()
    var typeBackground : LiveData<Boolean> = _TypeBackGround

    fun setTypeBackGround(set : Boolean){
        _TypeBackGround.value = set
    }

    fun getTypeBackGround() : Boolean? {
        return typeBackground.value
    }

    fun setMyMapType(mapType: MapType){
         when(mapType){
            MapType.Satellite -> { _MyMapType.value = MapType.Hybrid }
            MapType.Hybrid -> { _MyMapType.value = MapType.Standard}
            else -> { _MyMapType.value = MapType.Satellite }
        }
    }

//
//
//    private var _documentResultEvent = MutableLiveData<DocumentResult>()
//    val documentResultEvent: LiveData<DocumentResult> = _documentResultEvent
//    private val favoriteDocumentListCache: MutableList<Document> = ArrayList()
//
//    //
//    var selectPositionEvent = MutableLiveData<SelectPosition>()
//
//    val trackingLiveData = getMapUseCase.invoke().asLiveData()
//    //
//
//    private val _boolLiveData : MutableLiveData<Boolean> by lazy {
//        MutableLiveData<Boolean>()
//    }
//    val boolLiveData : LiveData<Boolean> = _boolLiveData
//
//
//
//    private fun scopeIsBool(bool : Boolean) =
//         viewModelScope.launch{
//                 setBool.setPutBoolean(bool)
//         }
//
//
//    fun setDataBool(){
//        boolLiveData.value?.let {
//            scopeIsBool(it)
//        }
//    }
//
//    fun getIsBool() : Boolean{
//        val reBool : Boolean = false
//        getBool.invoke().asLiveData().value?.let {
//            reBool == it
//        }
//        return reBool
//    }
//
//    fun enableTrackingMode() {
//        setTrackingMode(tempTrackingModeForEnable!!)
//    }
//
//    fun disableTrackingMode() {
//        setTrackingMode(CurrentLocationTrackingModel.TrackingModeOff)
//    }
//    fun toggleTrackingMode(){
//        var trackingMode = trackingLiveData.value
//        if(trackingMode == null){
//            trackingMode = CurrentLocationTrackingModel.TrackingModeOff
//        }
//        val newTrackingMode = when (trackingMode) {
//            CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> CurrentLocationTrackingMode.TrackingModeOnWithHeading
//            CurrentLocationTrackingModel.TrackingModeOnWithHeading -> CurrentLocationTrackingMode.TrackingModeOff
//            else -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
//        }
//        setTrackingMode(newTrackingMode)
//    }
//    private fun setTrackingMode(trackingMode: CurrentLocationTrackingModel) = viewModelScope.launch {
//        setMapDataTrackingUseCase.excute(trackingMode)
//    }
//
//    var tempTrackingModeForEnable : CurrentLocationTrackingModel?
//        get() {
//            val tempTrackingMode = savedStateHandle.remove<CurrentLocationTrackingMode>(
//                TEMP_TRACKING_MODE
//            )
//            return tempTrackingMode ?: CurrentLocationTrackingModel.TrackingModeOnWithoutHeading
//        }
//        set(trackingMode) {
//            savedStateHandle[TEMP_TRACKING_MODE] = trackingMode
//        }
//// Tracking ^^
//fun selectDocument(position: Int, selectedByMap: Boolean) {
//    selectPositionEvent.value = SelectPosition(position, selectedByMap)
//}
//    // Docu
//    val requireDocumentList get() = documentResultEvent.value!!.documentList
//    private fun setDocumentListWithFavorite(
//        documentList: List<Document>, favoriteDocumentList: List<Document>, isMoveCamera: Boolean
//    ) {
//        val newDocumentList = documentList.map {
//            val favoriteDocument = favoriteDocumentList.find { document1 -> document1.id == it.id }
//            it.copy(isFavorite = favoriteDocument != null)
//        }
//        _documentResultEvent.value = DocumentResult(newDocumentList, isMoveCamera)
//    }
//
//
//    init {
//
//        }
//
//    companion object {
//        private const val MAP_VIEW_MODE = "MAP_VIEW_MODE"
//        private const val LIST_MODE = "LIST_MODE"
//        private const val TEMP_TRACKING_MODE = "TEMP_TRACKING_MODE"
//    }
}