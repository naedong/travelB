package kr.tr.domain.usecase

import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.usecase.gateway.TourismGateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 2:50
 */
class TourismCodeUseCase @Inject constructor(
    private val gateWay: TourismGateWay
) {
    suspend operator fun invoke() : Response<TourismCode> {
        return gateWay.getTourismCodeGateWay()
    }
}