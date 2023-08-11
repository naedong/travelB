package kr.com.map.util

import kr.tr.domain.model.item.CurrentLocationTrackingModel

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 4:42
 */
fun CurrentLocationTrackingModel.isEnabled() : Boolean {
    return this != CurrentLocationTrackingModel.TrackingModeOff
}