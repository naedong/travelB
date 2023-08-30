package kr.tr.data.source.remote

import kr.tr.data.api.SubWayApiSerivce
import kr.tr.data.api.TourismApiService
import kr.tr.data.mapper.asSubWay
import kr.tr.data.mapper.asTourism
import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.model.item.TourismCode
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:34
 */
class SubWayRemoteDataSource @Inject constructor(
    private val subWayApiService: SubWayApiSerivce
){
    suspend fun getSubWayCode() : Response<BusanSubWayItem> {
        return subWayApiService.getSubWayStationCode().asSubWay()
    }
}
