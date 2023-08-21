package kr.tr.travelbproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.tr.data.repository.BusanRepository
import kr.tr.data.repository.TourismRepository
import kr.tr.data.repository.repositoryimpl.TourismRepositoryImpl
import kr.tr.domain.repository.TourismRepositoryInter
import kr.tr.domain.usecase.gateway.GateWay
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
    fun provideTravelBInter(
        repositoryInter: TourismRepositoryImpl
    ) : TourismRepositoryInter = repositoryInter

}
