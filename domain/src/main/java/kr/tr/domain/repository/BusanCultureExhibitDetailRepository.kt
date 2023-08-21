package kr.tr.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.datasource.BusanCultureExhibitDetailDataSource
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.usecase.BusanCultureExhibitDetailUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 11:46
 */
class BusanCultureExhibitDetailRepository @Inject constructor(
    private val busanCultureExhibitDetailUseCase: BusanCultureExhibitDetailUseCase
)
{
    fun getBusanCultureExhibitDetailPagingItem() : Flow<PagingData<BusanCultureExhibitDetailItem>> {
        return Pager(PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            enablePlaceholders = true
        )) {
            BusanCultureExhibitDetailDataSource(
                busanCultureExhibitDetailUseCase
            )
        }.flow
    }

}