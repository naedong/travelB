package kr.tr.data.repository.repositoryimpl

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.data.repository.datasource.AreaBaseDataSource
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.repository.AreaBasedItemRepositoryInter
import kr.tr.domain.usecase.AreaBasedItemUseCase
import kr.tr.domain.usecase.TourismCodeUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-21
 * Time: 오후 4:43
 */
class AreaBasedItemRepositroyImpl @Inject constructor(
    private val areaBasedItemUseCase: AreaBasedItemUseCase,
) : AreaBasedItemRepositoryInter {


    override fun getAreaBaseItemInter(sigunguCode: Int): Flow<PagingData<AreaBasedListItem>> {

        return Pager(
            PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = true
            )
        ) {
            AreaBaseDataSource(
                sigunguCode = sigunguCode,
                useCase = areaBasedItemUseCase
            )
        }.flow
    }
}

