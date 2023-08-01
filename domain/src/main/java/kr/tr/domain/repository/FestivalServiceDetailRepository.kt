package kr.tr.domain.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kr.tr.domain.datasource.BusanFestivalServiceDetailDataSource
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.domain.usecase.FestivalServiceUseCase
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-01
 * Time: 오후 12:15
 */
class FestivalServiceDetailRepository @Inject constructor(
    private  val festivalServiceUseCase: FestivalServiceUseCase
) {
      suspend fun getFestivalServiceDetail(ucSeq: Int): GetFestivalKrItem? {
         return BusanFestivalServiceDetailDataSource(festivalServiceUseCase).getFestivalServiceDetail(ucSeq)
       }


}
