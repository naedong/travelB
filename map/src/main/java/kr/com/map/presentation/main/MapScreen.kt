package kr.com.map.presentation.main

import android.Manifest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kr.com.map.item.MainFloatingActionButton
import kr.com.map.presentation.model.MapViewModel
import kr.tr.commom.R
import kr.tr.commom.theme.CustomMaterialTheme
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.MapType


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 1:16
 */
@Composable
fun MapRouter(navigation: NavHostController) {
    KakaoMapScreen()
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun KakaoMapScreen() {
    val viewModel = hiltViewModel<MapViewModel>()
    val mapType = viewModel.myMapType.observeAsState(MapType.Satellite)
    val slideBool = viewModel.typeBackground.observeAsState(initial = false)

    val rs = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    var mapView: MapView? = null


    AndroidView(factory = {
        MapView(it)
    }
    ) {
        it.mapType = mapType.value
    }

    Column {
        Text(text = "${mapType.value}")
        MainFloatingActionButton(onClick = {
            viewModel.setTypeBackGround(true)
            viewModel.setMyMapType(mapType.value)

        }) {
            val res = when (mapType.value) {
                MapType.Satellite -> R.drawable.baseline_gps_mode
                MapType.Hybrid -> R.drawable.baseline_comepass
                else -> R.drawable.baseline_gps_fixed
            }
            Image(painter = painterResource(id = res), contentDescription = "")
        }


    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom

    ) {
        mainVerticallySlide(viewModel)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun mainVerticallySlide(viewModel : MapViewModel) {

    AnimatedVisibility(
        visible = viewModel.getTypeBackGround() ?: false,
        enter = slideInVertically(initialOffsetY = {
            +it
        }),
        exit = slideOutVertically(targetOffsetY = {
            +it
        })
    ) {
        Box(
            modifier = Modifier
                .size(500.dp)
                .zIndex(1f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp, top = 15.dp),
                horizontalArrangement = Arrangement.End
                ) {
                Icon(Icons.Default.Close,
                    modifier = Modifier.
                    clickable {
                        viewModel.setTypeBackGround(false)
                    },
                    contentDescription = "")
            }

            Column {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .padding(start = 40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Card {
                        Text(
                            text = "지도",
                            fontFamily = CustomMaterialTheme.typography.maruBuri_SemiBold,
                            fontSize = 18.sp

                        )
                    }
                    Card {
                        Text(
                            text = "지도 + 스카이뷰",
                            fontFamily = CustomMaterialTheme.typography.maruBuri_SemiBold,
                            fontSize = 18.sp

                        )
                    }
                    Card {
                        Text(
                            text = "3D스카이뷰",
                            fontFamily = CustomMaterialTheme.typography.maruBuri_SemiBold,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier)
                }

            }


        }
    }
}

//@Composable
//fun MapScreen() {
//
//    val viewModel = hiltViewModel<MapViewModel>()
//    val trackingMode by viewModel.trackingLiveData.observeAsState(initial = CurrentLocationTrackingModel.TrackingModeOff)
//    var mapView: MapView? = null
//
//    MapScreenMapView(viewModel, trackingMode, mapView)
//
//
//}
//
//fun CurrentLocation(trackingMode: CurrentLocationTrackingModel) : CurrentLocationTrackingMode {
//    return when (trackingMode) {
//        CurrentLocationTrackingModel.TrackingModeOnWithMarkerHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithMarkerHeadingWithoutMapMoving
//        CurrentLocationTrackingModel.TrackingModeOnWithHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
//        CurrentLocationTrackingModel.TrackingModeOnWithHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
//        CurrentLocationTrackingModel.TrackingModeOnWithoutHeading -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
//        CurrentLocationTrackingModel.TrackingModeOnWithoutHeadingWithoutMapMoving -> CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving
//        else -> CurrentLocationTrackingMode.TrackingModeOff
//    }
//}
//
//
//@Composable
//fun MapScreenMapView(
//    viewModel: MapViewModel,
//    trackingMode: CurrentLocationTrackingModel,
//    mapView: MapView?
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//    ) {
//
//
//
//        AndroidView(factory =
//        {
//            MapView(it).apply {
//
//            }.also { item ->
//
//            }
//        })
//        Column {
//
//            MainFloatingActionButtons(
//                trackingMode = trackingMode,
//                onTrackingModeClick = { viewModel.toggleTrackingMode() },
//                onListModeClick = {
//
//                },
//            )
//        }
//
//    }
//
////    observeViewModel(viewModel, mapView)
//}
//
//private fun mapViewEventListeners(viewModel: MapViewModel) = object : BaseMapViewEventListener() {
//    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
//        viewModel.enableTrackingMode()
//    }
//
//    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
//    }
//
//    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
//        viewModel.disableTrackingMode()
//    }
//}
//
//
//private val poiItemEventListener = object : BasePOIItemEventListener() {
//    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
//
//
//        p0?.moveCamera(CameraUpdateFactory.newMapPoint(p1?.mapPoint))
//    }
//}
//
//private fun addPOIItemInMapView(mapPoint: MapPoint, name: String, view: MapView): MapPOIItem {
//    return MapPOIItem().apply {
//        itemName = name
//        isShowDisclosureButtonOnCalloutBalloon = false
//        this.mapPoint = mapPoint
//        markerType = MapPOIItem.MarkerType.BluePin
//    }.also {
//        view!!.addPOIItem(it)
//    }
//}
//
//private fun onDocumentClick(
//    document: Document,
//    position: Int,
//    mapView: MapView,
//    viewModel: MapViewModel
//) {
//    viewModel.selectDocument(position, false)
//    mapView!!.selectPOIItem(document.mapPOIItem, true)
//    mapView!!.moveCamera(CameraUpdateFactory.newMapPoint(document.mapPOIItem!!.mapPoint))
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//private fun observeViewModel(viewModel: MapViewModel, mapView: MapView?) {
//
//    val rs = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
//
//    val trackingLive = viewModel.trackingLiveData.observeAsState()
//    val boolLives = viewModel.boolLiveData.observeAsState()
//    val activity = LocalContext.current as Activity
//    val resultParam = remember { mutableStateOf<ActivityResult?>(null) }
//
//    val showPage = remember {
//        mutableStateOf(false)
//    }
//
//
//    val launcher: ActivityResultLauncher<Intent> = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult(),
//    ) {
//        resultParam.value == it
//    }
//    LaunchedEffect(trackingLive.value) {
//        // LiveData 값이 변경되면 실행되는 블록
//        val trackingMode = trackingLive.value
//        val boolLive = boolLives.value
//
//
//        // 변경된 LiveData 값에 대한 처리
//        boolLive?.let {
//            viewModel.setDataBool()
//        }
//
//        trackingMode?.let {
//            if (it.isEnabled()) {
//                showPage.value == true
//            }
//        }
//    }
//    if (showPage.value) {
//        trackingLive.value?.let {
//            AccessFineLocationUtil.PermissionScreen(
//                activity = activity,
//                viewModel = viewModel,
//                onPermissionGranted = {
//                    mapView?.currentLocationTrackingMode = CurrentLocation(it)
//                },
//                onRequestPermission = {
//                    viewModel.tempTrackingModeForEnable == it
//                },
//                onPermissionCanceled = {
//                    Log.e("Test", "취소")
//                },
//                resultRegister = launcher
//            )
//            if (!it.isEnabled()) {
//                mapView?.currentLocationTrackingMode = CurrentLocation(it)
//            }
//        }
//    }
//}





