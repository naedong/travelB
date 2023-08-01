package kr.tr.domain.usecase

import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.usecase.gateway.GateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-01
 * Time: 오후 12:07
 */
class FestivalServiceDetailUseCase @Inject constructor(
    private val gateWay: GateWay
) {
    suspend operator fun invoke(ucSeq : Int)  : Response<FestivalService>  {
        return gateWay.getFestivalServiceDetailGateWay(ucSeq)
    }
}