package kr.tr.domain.repository.local

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 1:14
 */
interface MapDataStoreRepository {
//    fun getTrackingMode(): Flow<CurrentLocationTrackingMode>
//    suspend fun setTrackingMode(trackingMode: CurrentLocationTrackingModel)
    suspend fun setPutBoolean(bool : Boolean)
    fun getBoolean() : Flow<Boolean>
}