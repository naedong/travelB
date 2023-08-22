package kr.tr.domain.usecase

import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.usecase.gateway.TourismGateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-21
 * Time: 오후 4:53
 */
class AreaBasedItemUseCase @Inject constructor(
    private val gateWay: TourismGateWay
) {
    suspend operator fun invoke(pageNo : Int, sigunguCode : Int) : Response<AreaBasedItem> {


        return gateWay.getAreaBasedItem(sigunguCode = sigunguCode, pageNo = pageNo)
    }

}