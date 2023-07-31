package kr.tr.travelbproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.tr.data.repository.BusanRepository
import kr.tr.domain.usecase.gateway.GateWay
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
}