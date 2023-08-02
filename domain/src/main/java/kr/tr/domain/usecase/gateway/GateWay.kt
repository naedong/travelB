package kr.tr.domain.usecase.gateway

import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.model.item.GetBusanCultreExhibitDetail
import retrofit2.Response

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 11:24
 */
interface GateWay {
    suspend fun getBusanCultreExhibitDetialGateWay(page : Int) : Response<GetBusanCultreExhibitDetail>
    suspend fun getFestivalServiceGateWay(page : Int) : Response<FestivalService>

   
}