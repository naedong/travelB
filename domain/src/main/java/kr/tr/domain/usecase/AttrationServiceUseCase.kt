package kr.tr.domain.usecase

import kr.tr.domain.model.item.AttractionServiceItem
import kr.tr.domain.usecase.gateway.GateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 3:37
 */
class AttrationServiceUseCase @Inject constructor(
    private val gateWay: GateWay
) {
    suspend operator fun invoke(page: Int): Response<AttractionServiceItem> {
        return gateWay.getAttrationServiceGateWay(page)
    }
}