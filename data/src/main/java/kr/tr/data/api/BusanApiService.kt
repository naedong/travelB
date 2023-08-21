package kr.tr.data.api

import kr.tr.data.model.item.ResponsGetBusanCultreExhibitDetail
import kr.tr.data.model.item.ResponseAttractionServiceItem
import kr.tr.data.model.item.ResponseFestivalService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:15
 */
interface BusanApiService {

    @GET("6260000/BusanCultureExhibitDetailService/getBusanCultureExhibitDetail")
    suspend fun getBusanCultureExhibitDetail(
        @Query("pageNo") pageNo : Int,
        @Query("numOfRows") numOfRows : Int,
        @Query("resultType") resultType : String = "JSON",
    ) : Response<ResponsGetBusanCultreExhibitDetail>


    @GET("6260000/FestivalService/getFestivalKr")
    suspend fun getFestivalKr(
        @Query("pageNo") pageNo : Int,
        @Query("numOfRows") numOfRows : Int,
        @Query("resultType") resultType : String = "JSON",
    ) : Response<ResponseFestivalService>

    @GET("6260000/FestivalService/getFestivalKr")
    suspend fun getFestivalKrDetail(
        @Query("UC_SEQ") ucSeq : Int,
        @Query("resultType") resultType : String = "JSON",
    ) : Response<ResponseFestivalService>

    @GET("6260000/AttractionService/getAttractionKr")
    suspend fun getAttractionKr(
        @Query("pageNo") pageNo : Int,
        @Query("numOfRows") numOfRows : Int,
        @Query("resultType") resultType : String = "JSON",
    ) : Response<ResponseAttractionServiceItem>






}