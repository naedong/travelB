package kr.tr.data.repository.repositoryimpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.data.repository.datasource.TourismDataSource
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.repository.TourismRepositoryInter
import kr.tr.domain.usecase.TourismCodeUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 3:24
 */
class TourismRepositoryImpl @Inject constructor(
        private val tourismCodeUseCase: TourismCodeUseCase
): TourismRepositoryInter {
        override fun invoke(): Flow<PagingData<TourismCodeItem>> {
                return Pager(
                        PagingConfig(
                                pageSize = 20,
                                prefetchDistance = 5,
                                enablePlaceholders = true
                        )
                ){
                        TourismDataSource(
                                tourismCodeUseCase
                        )
                }.flow
        }
}