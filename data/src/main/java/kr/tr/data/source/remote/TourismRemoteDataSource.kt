package kr.tr.data.source.remote

import android.util.Log
import kr.tr.data.api.TourismApiService
import kr.tr.data.mapper.asTourism
import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.TourismCode
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 3:30
 */
class TourismRemoteDataSource @Inject constructor(
    private val tourismApiService: TourismApiService
){

    suspend fun getTourismCode() : Response<TourismCode>{
        return tourismApiService.getTourismServiceCodeList().asTourism()
    }

    suspend fun getAreaBasedItem(sigunguCode : Int, pageNo : Int ) : Response<AreaBasedItem> {
        return tourismApiService.getTourismAreaBasedList(
            pageNo = pageNo,
            sigunguCode = sigunguCode
        ).asTourism()
    }


}