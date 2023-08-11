package kr.com.map.presentation.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kr.com.map.data.model.Document
import kr.com.map.item.MainFloatingActionButtons
import kr.com.map.mapeventlistener.BaseMapViewEventListener
import kr.com.map.mapeventlistener.BasePOIItemEventListener
import kr.com.map.presentation.model.MapViewModel
import kr.com.map.util.AccessFineLocationUtil
import kr.com.map.util.isEnabled
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 1:16
 */
@Composable
fun MapRouter(navigation: NavHostController) {
    MapScreen()
}

@Composable
fun MapScreen() {

    val viewModel = hiltViewModel<MapViewModel>()
    val trackingMode by viewModel.trackingLiveData.observeAsState(initial = CurrentLocationTrackingModel.TrackingModeOff)
    var mapView: MapView? = null

    MapScreenMapView(viewModel, trackingMode, mapView)
}


@Composable
fun MapScreenMapView(
    viewModel: MapViewModel,
    trackingMode: CurrentLocationTrackingModel,
    mapView: MapView?
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        AndroidView(factory =
        {
            MapView(it).apply {
                setMapViewEventListener(mapViewEventListeners(viewModel))
                setPOIItemEventListener(poiItemEventListener)
            }.also { item ->
                mapView?.run {
                    mapView == item.apply {
                        currentLocationTrackingMode = when (trackingMode) {
                            CurrentLocationTrackingModel.TrackingModeOnWithMarkerHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
                            CurrentLocationTrackingModel.TrackingModeOnWithHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                            CurrentLocationTrackingModel.TrackingModeOnWithHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
                            CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                            CurrentLocationTrackingModel.TrackingModeOnWithoutHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
                            else -> CurrentLocationTrackingMode.TrackingModeOff
                        }
                    }
                }
            }
        })
        Column {

            MainFloatingActionButtons(
                trackingMode = trackingMode,
                onTrackingModeClick = { viewModel.toggleTrackingMode() },
                onListModeClick = {

                },
            )
        }

    }

    observeViewModel(viewModel, mapView)
}

private fun mapViewEventListeners(viewModel: MapViewModel) = object : BaseMapViewEventListener() {
    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        viewModel.enableTrackingMode()
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        viewModel.disableTrackingMode()
    }
}


private val poiItemEventListener = object : BasePOIItemEventListener() {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {


        p0?.moveCamera(CameraUpdateFactory.newMapPoint(p1?.mapPoint))
    }
}

private fun addPOIItemInMapView(mapPoint: MapPoint, name: String, view: MapView): MapPOIItem {
    return MapPOIItem().apply {
        itemName = name
        isShowDisclosureButtonOnCalloutBalloon = false
        this.mapPoint = mapPoint
        markerType = MapPOIItem.MarkerType.BluePin
    }.also {
        view!!.addPOIItem(it)
    }
}

private fun onDocumentClick(
    document: Document,
    position: Int,
    mapView: MapView,
    viewModel: MapViewModel
) {
    viewModel.selectDocument(position, false)
    mapView!!.selectPOIItem(document.mapPOIItem, true)
    mapView!!.moveCamera(CameraUpdateFactory.newMapPoint(document.mapPOIItem!!.mapPoint))
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun observeViewModel(viewModel: MapViewModel, mapView: MapView?) {

    val rs = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    val trackingLive = viewModel.trackingLiveData.observeAsState()
    val boolLives = viewModel.boolLiveData.observeAsState()
    val activity = LocalContext.current as Activity
    val resultParam = remember { mutableStateOf<ActivityResult?>(null) }

    val showPage = remember {
        mutableStateOf(false)
    }


    val launcher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        resultParam.value == it
    }
    LaunchedEffect(trackingLive.value) {
        // LiveData 값이 변경되면 실행되는 블록
        val trackingMode = trackingLive.value
        val boolLive = boolLives.value


        // 변경된 LiveData 값에 대한 처리
        boolLive?.let {
            viewModel.setDataBool()
        }

        trackingMode?.let {
            if (it.isEnabled()) {
                showPage.value == true
            }
        }
    }
    if (showPage.value) {
        trackingLive.value?.let {
            AccessFineLocationUtil.PermissionScreen(
                activity = activity,
                viewModel = viewModel,
                onPermissionGranted = {
                    mapView?.currentLocationTrackingMode == when (it) {
                        CurrentLocationTrackingModel.TrackingModeOnWithMarkerHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
                        CurrentLocationTrackingModel.TrackingModeOnWithHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                        CurrentLocationTrackingModel.TrackingModeOnWithHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
                        CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                        CurrentLocationTrackingModel.TrackingModeOnWithoutHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
                        else -> CurrentLocationTrackingMode.TrackingModeOff
                    }
                },
                onRequestPermission = {
                    viewModel.tempTrackingModeForEnable == it
                },
                onPermissionCanceled = {
                    Log.e("Test", "취소")
                },
                resultRegister = launcher
            )
            mapView?.currentLocationTrackingMode == when (it) {
                CurrentLocationTrackingModel.TrackingModeOnWithMarkerHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
                CurrentLocationTrackingModel.TrackingModeOnWithHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                CurrentLocationTrackingModel.TrackingModeOnWithHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
                CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                CurrentLocationTrackingModel.TrackingModeOnWithoutHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
                else -> CurrentLocationTrackingMode.TrackingModeOff
            }

            if (!it.isEnabled()) {
                mapView?.currentLocationTrackingMode == when (it) {
                    CurrentLocationTrackingModel.TrackingModeOnWithMarkerHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
                    CurrentLocationTrackingModel.TrackingModeOnWithHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                    CurrentLocationTrackingModel.TrackingModeOnWithHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
                    CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                    CurrentLocationTrackingModel.TrackingModeOnWithoutHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
                    else -> CurrentLocationTrackingMode.TrackingModeOff
                }

            }
        }
    }
}





