package kr.tr.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.datasource.BusanFestivalServiceDataSource
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.domain.usecase.FestivalServiceUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오전 10:35
 */
class FestivalServiceRepository @Inject constructor(
    private val festivalService: FestivalServiceUseCase
) {
    fun getFestivalServiceItem() : Flow<PagingData<GetFestivalKrItem>> {
        return Pager(
            PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = true
        )
        ) {
            BusanFestivalServiceDataSource(
                festivalService
            )
        }.flow

    }




}