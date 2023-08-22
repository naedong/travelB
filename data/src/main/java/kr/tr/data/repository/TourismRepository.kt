package kr.tr.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.data.source.remote.TourismRemoteDataSource
import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.repository.TourismRepositoryInter
import kr.tr.domain.usecase.gateway.TourismGateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 4:43
 */
class TourismRepository @Inject constructor(
    private val tourismRemoteDataSource: TourismRemoteDataSource
) : TourismGateWay {
    override suspend fun getTourismCodeGateWay(): Response<TourismCode> {
        return tourismRemoteDataSource.getTourismCode()
    }

    override suspend fun getAreaBasedItem(sigunguCode: Int, pageNo: Int): Response<AreaBasedItem> {
        return  tourismRemoteDataSource.getAreaBasedItem(sigunguCode = sigunguCode,pageNo = pageNo)
    }

}