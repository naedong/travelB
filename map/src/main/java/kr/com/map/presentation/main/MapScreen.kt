package kr.com.map.presentation.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kr.com.map.item.MainFloatingActionButton
import kr.com.map.item.MapTypeCardItem
import kr.com.map.item.permissionCheck
import kr.com.map.mapeventlistener.BasePOIItemEventListener
import kr.com.map.presentation.model.MapViewModel
import kr.tr.commom.R
import kr.tr.commom.items.DefaultSubject
import kr.tr.commom.items.RegularFloatingActionButtonItem
import kr.tr.commom.theme.CustomMaterialTheme
import kr.tr.commom.utill.GetPermissions
import kr.tr.domain.model.item.MapLocationBasedItemItem
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode
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

@Composable
fun KakaoMapScreen() {
    val viewModel = hiltViewModel<MapViewModel>()
    val mapType = viewModel.myMapType.observeAsState(MapType.Standard)
    val slideBool = viewModel.typeBackground.observeAsState(initial = false)
    val tracking =
        viewModel.trackingMode.observeAsState(initial = CurrentLocationTrackingMode.TrackingModeOff)
    val gpsMap = viewModel.useGPS.observeAsState()
    var mapViews: MapView? = null
    val sliderValue = viewModel.length.observeAsState(500f)
    val defaultMapItem = viewModel.getMapLocation().collectAsLazyPagingItems()
    val poiMarkers = viewModel.markers.observeAsState()
    val permissionBool = rememberSaveable {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val keyWords = rememberSaveable { mutableStateOf("") }
    val fusedLocationProviderClient =
        remember {
            LocationServices.getFusedLocationProviderClient(context)
        }


    GetPermissions(context = context)
    if (permissionBool.value == true) {
        permissionCheck(viewModel, tracking, permissionBool)
    }

    DisposableEffect(context) {
        val request = getMyLocation(context, fusedLocationProviderClient, viewModel)

        when (keyWords.value) {
            "식당" -> {}
            "ATM" -> {}
            else -> {}
        }

        onDispose {

        }
    }

    LazyColumn {
        items(defaultMapItem) {
            if (tracking.value != CurrentLocationTrackingMode.TrackingModeOff) {
                if (it != null) {
                    Log.e("MapView", "$defaultMapItem")

                }
//                    mapViews?.let { it1 -> addPOIItem(defaultItem, it1) } }
            }
        }
    }

    val openCloseVisible = rememberSaveable {
        mutableStateOf(false)
    }

    var searchText by remember {
        mutableStateOf("")
    }

    BackHandler(openCloseVisible.value) {
        openCloseVisible.value = false
    }
    AndroidView(
        factory = {
            MapView(it).apply {
                setPOIItemEventListener(poiItemEventListener(viewModel))
            }.also {
                mapViews = it.apply {

                }
            }
        },
    ) {
        it.mapType = mapType.value
        it.currentLocationTrackingMode = tracking.value
        it.mapCenterPoint.mapPointGeoCoord.latitude = viewModel.useGPS.value?.get(0) ?: 37.508
        it.mapCenterPoint.mapPointGeoCoord.longitude = viewModel.useGPS.value?.get(1) ?: 127.060
        it.setMapViewEventListener {
            it.isHDMapTileEnabled = true
        }
    }




    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End
    ) {
        searchShop(
            this,
            searchText,
            openCloseVisible,
            onSearchTextChanged = { searchText = it },
            onSearchButtonClicked = { openCloseVisible.value = true },
            focusManager = focusManager,
        )

        MainFloatingActionButton(onClick = {
            viewModel.setTypeBackGround(slideBool.value.not())
        }) {
            val res = when (slideBool.value) {
                true -> R.drawable.blue_web_stories_24
                else -> R.drawable.web_stories_24
            }
            Image(
                painter = painterResource(id = res),
                contentDescription = ""
            )
        }


        MainFloatingActionButton(onClick = {
            permissionBool.value = true
        }
        ) {
            val res = when (tracking.value) {
                CurrentLocationTrackingMode.TrackingModeOff -> R.drawable.baseline_gps_fixed
                CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving -> R.drawable.baseline_navigation_24
                else -> R.drawable.baseline_gps_mode
            }
            Image(
                painter = painterResource(id = res),
                contentDescription = "",
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom

    ) {

        mainVerticallySlide(viewModel, slideBool, sliderValue)
    }
}


@Composable
fun searchShop(
    columnScope: ColumnScope,
    searchText: String,
    openCloseVisible: MutableState<Boolean>,
    onSearchTextChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    focusManager: FocusManager,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        AnimatedVisibility(visible = openCloseVisible.value,
            modifier = Modifier.offset(x = 0.dp),
            enter =
            slideInHorizontally {
                +it
            } + scaleIn(
                initialScale = 1f
            ),
            exit = slideOutHorizontally
            {
                +it
            } + scaleOut(
                transformOrigin = TransformOrigin(1f, 0f)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(20.dp)
                    )
            ) {
                BasicTextField(
                    modifier = Modifier,
                    value = searchText,
                    onValueChange = onSearchTextChanged,
                    singleLine = true,
                    cursorBrush = SolidColor(MaterialTheme.colors.primary),
                    textStyle = TextStyle(
                        fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                        fontSize = 12.sp,
                        color = Color.Black
                    ),
                    decorationBox = { innerTextField ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            IconButton(onClick = {
                                openCloseVisible.value = false
                                focusManager.clearFocus()
                            }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon",
                                    tint = Color.Black.copy(alpha = 0.5f)
                                )
                            }
                            Box(Modifier.weight(1f)) {
                                if (searchText.isEmpty()) {
                                    Text(
                                        text = "검색어를 입력하세요.",
                                        style = LocalTextStyle.current.copy(
                                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                                            fontSize = 15.sp,
                                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur
                                        )
                                    )
                                } else {
                                    innerTextField()
                                }
                            }
                        }
                    }
                )
            }
        }
        if (!openCloseVisible.value) {
            columnScope.MainFloatingActionButton(onClick = onSearchButtonClicked) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            }
        }
    }
}


fun setTrackingMode(mode: CurrentLocationTrackingMode): CurrentLocationTrackingMode {
    return when (mode) {
        CurrentLocationTrackingMode.TrackingModeOff -> {
            CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
        }

        CurrentLocationTrackingMode.TrackingModeOnWithoutHeading -> {
            CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving
        }

        else -> {
            CurrentLocationTrackingMode.TrackingModeOff
        }
    }

}


@Composable
fun mainVerticallySlide(
    viewModel: MapViewModel,
    slideBool: State<Boolean>,
    sliderValue: State<Float>
) {
    AnimatedVisibility(
        visible = slideBool.value,
        enter = slideInVertically(initialOffsetY = {
            +it
        }),
        exit = slideOutVertically(targetOffsetY = {
            +it
        })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .zIndex(1f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White),
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))

            DefaultSubject("지도 설정")

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 15.dp, top = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    viewModel.setTypeBackGround(false)
                }
                ) {
                    Icon(
                        Icons.Default.Close,
                        modifier = Modifier,
                        contentDescription = ""
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                Spacer(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    MapTypeCardItem(tagName = "지도", R.drawable.default_map, viewModel)
                    MapTypeCardItem(tagName = "지도 + 스카이뷰", R.drawable.hybrid, viewModel)
                    MapTypeCardItem(tagName = "3D 스카이뷰", R.drawable.satellite, viewModel)

                }
                Divider(
                    modifier = Modifier.padding(
                        top = 0.dp
                    )
                )

                DefaultSubject("편의 시설")

                Spacer(modifier = Modifier.padding(bottom = 10.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {


                    RegularFloatingActionButtonItem(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp),
                        onClick = {
                            viewModel.setTypeBackGround(slideBool.value.not())
                        }) {
                        Text(
                            "축 제",
                            modifier = Modifier,
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 15.sp
                        )
                    }

                    RegularFloatingActionButtonItem(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp),
                        onClick = { viewModel.setTypeBackGround(slideBool.value.not()) }) {
                        Text(
                            "편의점",
                            modifier = Modifier,
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp
                        )
                    }

                    RegularFloatingActionButtonItem(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp),
                        onClick = { viewModel.setTypeBackGround(slideBool.value.not()) }

                    ) {
                        Text(
                            "ATM",
                            modifier = Modifier,
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp
                        )
                    }
                    RegularFloatingActionButtonItem(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp),
                        onClick = { viewModel.setTypeBackGround(slideBool.value.not()) }) {
                        Text(
                            "식 당",
                            modifier = Modifier,
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp
                        )
                    }
                    RegularFloatingActionButtonItem(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 8.dp),
                        onClick = { viewModel.setTypeBackGround(slideBool.value.not()) }) {
                        Text(
                            "주차장",
                            modifier = Modifier,
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp
                        )
                    }
                }


                Divider(
                    modifier = Modifier.padding(
                        top = 25.dp, bottom = 5.dp
                    )
                )
                DefaultSubject("검색 거리")


                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Slider(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            colors = SliderDefaults.colors(
                                activeTrackColor = CustomMaterialTheme.colorScheme.mySchemePrimary,
                                activeTickColor = Color.Black,
                                thumbColor = Color.Black
                            ),
                            value = sliderValue.value,
                            onValueChangeFinished = {
                            },
                            onValueChange = {
                                viewModel.setLength(it)
                            },
                            steps = 39,
                            valueRange = 0f..20000f
                        )
                        Spacer(modifier = Modifier.padding(top = 10.dp))

                        Text(
                            text = "${sliderValue.value.toInt()}m",
                            fontFamily = CustomMaterialTheme.typography.hakgyoanasimwoojur,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    }

                }
            }
        }
    }
}

private fun getMyLocation(
    context: Context,
    fusedLocationProviderClient: FusedLocationProviderClient,
    viewModel: MapViewModel,

    ) {
    // 위치 매니저 초기화
    val request = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        viewModel.length.value?.toLong() ?: 500L
    ).setWaitForAccurateLocation(false).build()


    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // Handle received location updates
            for (location in locationResult.locations) {
                // Process the location data
                val latitude = location.latitude
                val longitude = location.longitude
                viewModel.setUseGPS(latitude, longitude)
            }
        }
    }
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return
    }
    fusedLocationProviderClient.requestLocationUpdates(request, locationCallback, null)
}
private fun poiItemEventListener(mapViewModel: MapViewModel) = object : BasePOIItemEventListener() {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        if (mapViewModel.mapViewMode.isNotDefault) {
            p1?.let {
                selectDocumentBy(it)
            }
        }
        p0?.let {
            it.moveCamera(CameraUpdateFactory.newMapPoint(checkNotNull(p1?.mapPoint)))
        }
    }
    private fun selectDocumentBy(mapPOIItem: MapPOIItem) {
        val documentList = mapViewModel.requireDocumentList
        documentList.find { it.mapPOIItem == mapPOIItem }?.let {
            val index = documentList.indexOf(it)
            mapViewModel.selectDocument(index, true)
        }
    }
}


private fun addPOIItem(defaultItem: MapLocationBasedItemItem?, mapViewModel: MapView): MapPOIItem {
    if (defaultItem == null) return MapPOIItem()

    return MapPOIItem().apply {
        defaultItem?.let {
            itemName = it.title
            isShowDisclosureButtonOnCalloutBalloon = false
            markerType = MapPOIItem.MarkerType.RedPin
            this.mapPoint = MapPoint.mapPointWithGeoCoord(
                it.mapx.toDouble() ?: 37.507,
                it.mapy.toDouble() ?: 127.530
            )
        }
    }.also {
        mapViewModel.addPOIItem(it)
    }
}

