package kr.tr.travelbproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kr.tr.data.repository.local.MapDataStoreRepositoryImpl
import kr.tr.domain.datasource.MapDataStore
import kr.tr.domain.repository.local.MapDataStoreRepository

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오후 3:00
 */
@Module
@InstallIn(ViewModelComponent::class)
object LoaclRepoModule {

    @Provides
    @ViewModelScoped
    fun getMapTrackingDataStore(
        mapDataStore : MapDataStore
    ): MapDataStoreRepository = MapDataStoreRepositoryImpl(mapDataStore)


}