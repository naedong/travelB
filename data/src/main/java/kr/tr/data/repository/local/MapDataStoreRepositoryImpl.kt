package kr.tr.data.repository.local

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.datasource.MapDataStore
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import kr.tr.domain.repository.local.MapDataStoreRepository
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 1:41
 */
class MapDataStoreRepositoryImpl @Inject constructor(
    private val mapDataStore: MapDataStore
) : MapDataStoreRepository {
    override fun getTrackingMode(): Flow<CurrentLocationTrackingModel> {
            return mapDataStore.getTrackingMode()
    }

    override suspend fun setTrackingMode(trackingMode: CurrentLocationTrackingModel) {
        mapDataStore.setTrackingModel(trackingMode)
    }

    override suspend fun setPutBoolean(bool: Boolean) {
        mapDataStore.isPutBoolean(bool)
    }

    override fun getBoolean(): Flow<Boolean> {
        return mapDataStore.isGetBoolean
    }
}