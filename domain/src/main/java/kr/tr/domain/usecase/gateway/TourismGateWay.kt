package kr.tr.domain.usecase.gateway

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.model.item.TourismCodeItem
import retrofit2.Response

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 3:25
 */
interface TourismGateWay  {
    suspend fun getTourismCodeGateWay() : Response<TourismCode>

    suspend fun getAreaBasedItem(sigunguCode : Int, pageNo : Int) : Response<AreaBasedItem>
}