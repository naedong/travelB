package kr.tr.domain.usecase.gateway

import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.model.item.TourismCode
import retrofit2.Response

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:10
 */
interface SubWayGateWay  {
    suspend fun getStationCodeGateWay() : Response<BusanSubWayItem>
}