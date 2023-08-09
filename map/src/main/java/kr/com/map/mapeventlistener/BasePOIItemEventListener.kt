package kr.com.map.mapeventlistener

import android.util.Log
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.POIItemEventListener

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-07
 * Time: 오후 1:46
 */
open class BasePOIItemEventListener : POIItemEventListener {
    override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
            Log.e("onPOIItemSelected", "p0 : $p0")
        Log.e("onPOIItemSelected", "p1 : $p1")



    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {


        Log.e("onCalloutBalloonOfPOIItemTouched" ," p0 : $p0")
        Log.e("onCalloutBalloonOfPOIItemTouched", " p0 : $p1")

    }

    override fun onCalloutBalloonOfPOIItemTouched(
        p0: MapView?,
        p1: MapPOIItem?,
        p2: MapPOIItem.CalloutBalloonButtonType?
    ) {

        Log.e("onCalloutBalloonOfPOIItemTouched", "p0 : $p0")

        Log.e("onCalloutBalloonOfPOIItemTouched", "p0 : $p1")

        Log.e("onCalloutBalloonOfPOIItemTouched", "p0 : $p2")
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

        Log.e("onDraggablePOIItemMoved", "p0 : $p0")

        Log.e("onDraggablePOIItemMoved", "p0 : $p1")

        Log.e("onDraggablePOIItemMoved", "p0 : $p2")
    }
}