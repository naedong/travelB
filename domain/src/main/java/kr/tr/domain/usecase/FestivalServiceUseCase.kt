package kr.tr.domain.usecase

import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.usecase.gateway.GateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오전 10:30
 */
class FestivalServiceUseCase @Inject constructor(
    private val gateWay: GateWay
) {
    suspend operator fun invoke(page : Int) : Response<FestivalService> {
        return gateWay.getFestivalServiceGateWay(page)
    }
}