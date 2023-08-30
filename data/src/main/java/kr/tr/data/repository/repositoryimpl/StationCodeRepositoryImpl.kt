package kr.tr.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.data.repository.datasource.StationCodeDataSource
import kr.tr.domain.model.item.SubWayItemData
import kr.tr.domain.repository.getStationCodeRepositoryInter
import kr.tr.domain.usecase.StationCodeUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:15
 */
class StationCodeRepositoryImpl @Inject constructor(
    val stationCode : StationCodeUseCase
) : getStationCodeRepositoryInter {

    override fun invoke(): Flow<PagingData<SubWayItemData>> {

        return Pager(PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = true
        )){
            StationCodeDataSource(
                stationCode
            )
        }.flow
    }
}