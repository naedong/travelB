package kr.tr.domain.usecase


import kr.tr.domain.model.item.GetBusanCultreExhibitDetail
import kr.tr.domain.usecase.gateway.GateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:35
 */
class BusanCultureExhibitDetailUseCase @Inject constructor(
    private val gateWay : GateWay
) {
    suspend operator fun invoke(page : Int) : Response<GetBusanCultreExhibitDetail> {
         return gateWay.getBusanCultreExhibitDetialGateWay(page)
        }

}