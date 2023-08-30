package kr.tr.data.api

import kr.tr.data.model.item.ResponseBusanSubWayItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 10:49
 */
interface SubWayApiSerivce {

    @GET("15043686/v1/uddi:cd7b3b1f-4a1c-4ba4-945b-94bebf9048e4_202002281723")
    suspend fun getSubWayStationCode(
        @Query("page") page : Int = 1,
        @Query("perPage") perPage : Int = 114,
        @Query("returnType") returnType: String = "json",
        ) : Response<ResponseBusanSubWayItem>
}

