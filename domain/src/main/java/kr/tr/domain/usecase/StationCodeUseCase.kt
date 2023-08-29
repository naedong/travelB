package kr.tr.domain.usecase

import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.usecase.gateway.SubWayGateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:11
 */
class StationCodeUseCase @Inject constructor(
    private val gateWay: SubWayGateWay
) {
    suspend operator fun invoke() : Response<BusanSubWayItem> {
        return gateWay.getStationCodeGateWay()
    }
}