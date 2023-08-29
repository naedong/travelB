package kr.tr.travelbproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.tr.domain.datasource.ListDataStore
import kr.tr.domain.datasource.MapDataStore
import javax.inject.Singleton

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오전 11:53
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providerAppDataStore(@ApplicationContext context: Context) : MapDataStore {
        return MapDataStore(context)
    }

    @Provides
    @Singleton
    fun providerListDataStore(@ApplicationContext context: Context) : ListDataStore {
        return ListDataStore(context)
    }




}