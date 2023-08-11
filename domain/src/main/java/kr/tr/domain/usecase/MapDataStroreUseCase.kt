package kr.tr.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import net.daum.mf.map.api.MapView

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 1:33
 */

    fun interface getMapDataTrackingUseCase : () -> Flow<CurrentLocationTrackingModel>
    fun interface setMapDataTrackingUseCase {
        suspend fun excute(setTracking : CurrentLocationTrackingModel)
    }

    fun interface setPutBoolean {
        suspend fun setPutBoolean(bool : Boolean)
    }

    fun interface getBool : () -> Flow<Boolean>


