package kr.com.map.mapeventlistener

import android.util.Log
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.MapViewEventListener

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 1:52
 */
open class BaseMapViewEventListener : MapViewEventListener {

    override fun onMapViewInitialized(p0: MapView?) {
        Log.e("onMapViewInitialized", "p0 : $p0")
    }

    override fun onMapViewCenterPointMoved(p0: MapView?, p1: MapPoint?) {

        Log.e("onMapViewCenterPointMoved", "p0 : $p0")
        Log.e("onMapViewCenterPointMoved", "p1 : $p1")
    }

    override fun onMapViewZoomLevelChanged(p0: MapView?, p1: Int) {

        Log.e("onMapViewZoomLevelChanged", "p0 : $p0")
        Log.e("onMapViewZoomLevelChanged", "p1 : $p1")
    }

    override fun onMapViewSingleTapped(p0: MapView?, p1: MapPoint?) {

        Log.e("onMapViewSingleTapped", "p0 : $p0")
        Log.e("onMapViewSingleTapped", "p1 : $p1")
    }

    override fun onMapViewDoubleTapped(p0: MapView?, p1: MapPoint?) {
        Log.e("onMapViewDoubleTapped", "p0 : $p0")
        Log.e("onMapViewDoubleTapped", "p1 : $p1")
    }

    override fun onMapViewLongPressed(p0: MapView?, p1: MapPoint?) {
        Log.e("onMapViewLongPressed", "p0 : $p0")
        Log.e("onMapViewLongPressed", "p1 : $p1")
    }

    override fun onMapViewDragStarted(p0: MapView?, p1: MapPoint?) {
        Log.e("onMapViewDragStarted", "p0 : $p0")
        Log.e("onMapViewDragStarted", "p1 : $p1")
    }

    override fun onMapViewDragEnded(p0: MapView?, p1: MapPoint?) {
        Log.e("onMapViewDragEnded", "p0 : $p0")
        Log.e("onMapViewDragEnded", "p1 : $p1")
    }

    override fun onMapViewMoveFinished(p0: MapView?, p1: MapPoint?) {
        Log.e("onMapViewMoveFinished", "p0 : $p0")
        Log.e("onMapViewMoveFinished", "p1 : $p1")
    }
}