package kr.tr.domain.repository.local

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.CurrentLocationTrackingModel

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 1:14
 */
interface MapDataStoreRepository {
    fun getTrackingMode(): Flow<CurrentLocationTrackingModel>
    suspend fun setTrackingMode(trackingMode: CurrentLocationTrackingModel)
    suspend fun setPutBoolean(bool : Boolean)
    fun getBoolean() : Flow<Boolean>
}