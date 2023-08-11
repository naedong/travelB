package kr.tr.data.repository


import android.util.Log
import kr.tr.data.source.remote.RemoteDataSource
import kr.tr.domain.model.item.AttractionServiceItem
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.model.item.GetBusanCultreExhibitDetail
import kr.tr.domain.usecase.gateway.GateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:40
 */
class BusanRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : GateWay {
    override suspend fun getBusanCultreExhibitDetialGateWay(page: Int): Response<GetBusanCultreExhibitDetail> {
        return remoteDataSource.getBusanCultureExhibitDetail(page)
    }

    override suspend fun getFestivalServiceGateWay(page: Int): Response<FestivalService> {
        return remoteDataSource.getBusanFestivalServiceKr(page)
    }

    override suspend fun getAttrationServiceGateWay(page: Int): Response<AttractionServiceItem> {
        return remoteDataSource.getAttrationServiceKr(page)
    }
}