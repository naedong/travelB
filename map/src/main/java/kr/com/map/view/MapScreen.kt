package kr.com.map.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import kr.com.map.mapeventlistener.BaseMapViewEventListener
import kr.com.map.mapeventlistener.BasePOIItemEventListener
import kr.tr.commom.utill.PermissionCheck
import net.daum.mf.map.api.CameraUpdateFactory
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

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

    MapScreenMapView()
}

private var mapView: MapView? = null

@Composable
fun MapScreenMapView() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {

        AndroidView(factory =
        {
            MapView(it).apply {
                setMapViewEventListener(mapViewEventListeners)
                setPOIItemEventListener(poiItemEventListener)
            }.also { item ->
                mapView = item.apply {
                    currentLocationTrackingMode
                }
            }
        })



    }
}


private val mapViewEventListeners = object : BaseMapViewEventListener() {
    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {

    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {

    }
}

private val poiItemEventListener = object : BasePOIItemEventListener() {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
        mapView?.moveCamera(CameraUpdateFactory.newMapPoint(p1?.mapPoint))
    }
}
