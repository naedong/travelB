package kr.tr.domain.model.item

import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 1:46
 */
enum class CurrentLocationTrackingModel {
    TrackingModeOff,
    TrackingModeOnWithoutHeading,
    TrackingModeOnWithHeading,
    TrackingModeOnWithoutHeadingWithoutMapMoving,
    TrackingModeOnWithHeadingWithoutMapMoving,
   TrackingModeOnWithMarkerHeadingWithoutMapMoving,


}

