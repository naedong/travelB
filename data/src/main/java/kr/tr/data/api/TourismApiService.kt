package kr.tr.data.api

import android.content.Context
import android.content.pm.PackageInfo
import kr.tr.commom.R
import kr.tr.data.model.item.ResponsGetBusanCultreExhibitDetail
import kr.tr.data.model.item.ResponseAreaBasedItem
import kr.tr.data.model.item.ResponseTourismCode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 2:38
 */
interface TourismApiService {

    @GET("B551011/KorService1/areaCode1")
    suspend fun getTourismServiceCodeList(
        // 고정 값이기에 AND 수를 고정
        @Query("MobileOS") mobileOs : String = "AND",
        // 고정 값이기에 16이란 수를 고정
        @Query("numOfRows") numOfRows : Int = 16,
        @Query("_type") type : String = "json",
        // 부산의 어플리케이션이 목적이기에 6 이란 수를 고정
        @Query("areaCode") areaCode : Int = 6,
        @Query("MobileApp") mobileApp : String = "${R.string.app_name}"
    ) : Response<ResponseTourismCode>


    @GET("B551011/KorService1/areaBasedList1")
    suspend fun getTourismAreaBasedList(
        // 고정 값이기에 AND 수를 고정
        @Query("MobileOS") mobileOs : String = "AND",
        // 기본 10개 씩 불러오기
        @Query("numOfRows") numOfRows : Int = 10,
        // 가져오는 데이터 타입 JSON
        @Query("_type") type : String = "json",
        // 부산의 어플리케이션이 목적이기에 6 이란 수를 고정
        @Query("areaCode") areaCode : Int = 6,
        @Query("MobileApp") mobileApp : String = "${R.string.app_name}",
        @Query("sigunguCode") sigunguCode : Int,
        @Query("pageNo") pageNo : Int,
        // 정렬구분 (A=제목순, C=수정일순, D=생성일순) 대표이미지가반드시있는정렬(O=제목순, Q=수정일순, R=생성일순) 가본 교육
        @Query("arrange") arrange : String = "A",
        // 목록구분(Y=목록, N=개수)
        @Query("listYN") listYN : String = "Y"
    ) : Response<ResponseAreaBasedItem>





}