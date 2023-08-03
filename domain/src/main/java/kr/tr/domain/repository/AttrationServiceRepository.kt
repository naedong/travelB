package kr.tr.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.datasource.AttrationServiceDataSource
import kr.tr.domain.model.item.AttractionServiceItem
import kr.tr.domain.model.item.GetAttractionKrItem
import kr.tr.domain.usecase.AttrationServiceUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 3:45
 */
class AttrationServiceRepository @Inject constructor(
    private val useCase: AttrationServiceUseCase
) {

    fun getAttrationServiceRepository() : Flow<PagingData<GetAttractionKrItem>>{
        return Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = true
            )
        ){
            AttrationServiceDataSource(
                useCase
            )
        }.flow
    }

}