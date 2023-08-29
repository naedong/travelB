package kr.tr.travelbproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.tr.data.repository.BusanRepository
import kr.tr.data.repository.SubwayRepository
import kr.tr.data.repository.TourismRepository
import kr.tr.data.repository.repositoryimpl.AreaBasedItemRepositroyImpl
import kr.tr.data.repository.repositoryimpl.StationCodeRepositoryImpl
import kr.tr.data.repository.repositoryimpl.TourismRepositoryImpl
import kr.tr.domain.repository.AreaBasedItemRepositoryInter
import kr.tr.domain.repository.TourismRepositoryInter
import kr.tr.domain.repository.getStationCodeRepositoryInter
import kr.tr.domain.usecase.gateway.GateWay
import kr.tr.domain.usecase.gateway.SubWayGateWay
import kr.tr.domain.usecase.gateway.TourismGateWay
import javax.inject.Singleton

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-27
 * Time: 오후 3:57
 */
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideTravelBGateWay(
     repository: BusanRepository
    ) : GateWay = repository

    @Singleton
    @Provides
    fun provideTravelBTourismGateWay(
        repository: TourismRepository
    ) :  TourismGateWay = repository

    @Singleton
    @Provides
    fun provideTravelBSubWayGateWay(
        repository: SubwayRepository
    ) :  SubWayGateWay = repository


    @Singleton
    @Provides
    fun provideTravelBInter(
        repositoryInter: TourismRepositoryImpl
    ) : TourismRepositoryInter = repositoryInter


    @Singleton
    @Provides
    fun provideTravelBAreaBaseInter(
        repositoryInter: AreaBasedItemRepositroyImpl
    ) : AreaBasedItemRepositoryInter = repositoryInter

    @Singleton
    @Provides
    fun provideTravelBSubWayInter(
        repositoryInter: StationCodeRepositoryImpl
    ) : getStationCodeRepositoryInter = repositoryInter


}
