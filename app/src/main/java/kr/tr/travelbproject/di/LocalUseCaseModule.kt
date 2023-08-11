package kr.tr.travelbproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kr.tr.data.repository.local.MapDataStoreRepositoryImpl
import kr.tr.domain.datasource.MapDataStore
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import kr.tr.domain.repository.local.MapDataStoreRepository
import kr.tr.domain.usecase.getBool

import kr.tr.domain.usecase.getMapDataTrackingUseCase
import kr.tr.domain.usecase.setMapDataTrackingUseCase
import kr.tr.domain.usecase.setPutBoolean

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 2:49
 */
@Module
@InstallIn(ViewModelComponent::class)
object LocalUseCaseModule {


    @Provides
    @ViewModelScoped
    fun getMapTracking(map : MapDataStoreRepository
    ): getMapDataTrackingUseCase =  getMapDataTrackingUseCase(map::getTrackingMode)

    @Provides
    @ViewModelScoped
    fun setMapTracking(map: MapDataStoreRepository)
     = object : setMapDataTrackingUseCase {
        override suspend fun excute(setTracking: CurrentLocationTrackingModel) {
            map.setTrackingMode(setTracking)
        }
    }

    @Provides
    @ViewModelScoped
    fun setPutBoolean(map: MapDataStoreRepository)
            = object : setPutBoolean {
        override suspend fun setPutBoolean(bool: Boolean) {
            map.setPutBoolean(bool)
        }
    }

    @Provides
    @ViewModelScoped
    fun getBool(map : MapDataStoreRepository
    ): getBool =  getBool(map::getBoolean)

}