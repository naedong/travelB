package kr.tr.data.model.item

import com.google.gson.annotations.SerializedName
import kr.tr.data.model.ResponseHeader


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-28
 * Time: 오후 4:42
 */

data class ResponseFestivalService(
    @SerializedName("getFestivalKr")
    val getFestivalKr: ResponseGetFestivalKr
)

data class ResponseGetFestivalKr(
    @SerializedName("header")
    val header: ResponseHeader,
    @SerializedName("item")
    val item: List<ResponseGetFestivalKrItem>,
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)

data class ResponseGetFestivalKrItem(
    @SerializedName("addr1")
    val addr1: String,
    @SerializedName("addr2")
    val addr2: String,
    @SerializedName("CNTCT_TEL")
    val cntctTel: String,
    @SerializedName("GUGUN_NM")
    val gugunNm : String,
    @SerializedName("HOMEPAGE_URL")
    val homepageUrl: String,
    @SerializedName("ITEMCNTNTS")
    val itemcntnts: String,
    @SerializedName("LAT")
    val lat: Double,
    @SerializedName("LNG")
    val lng: Double,
    @SerializedName("MAIN_IMG_NORMAL")
    val mainImgNormal : String,
    @SerializedName("MAIN_IMG_THUMB")
    val mainImgThumb: String,
    @SerializedName("MAIN_PLACE")
    val mainPlace: String,
    @SerializedName("MAIN_TITLE")
    val mainTitle : String,
    @SerializedName("MIDDLE_SIZE_RM1")
    val middelSizeRm1 : String,
    @SerializedName("PLACE")
    val place : String,
    @SerializedName("SUBTITLE")
    val subtitle : String,
    @SerializedName("TITLE")
    val title: String,
    @SerializedName("TRFC_INFO")
    val trfcInfo : String,
    @SerializedName("UC_SEQ")
    val ucSeq : Int,
    @SerializedName("USAGE_AMOUNT")
    val usageAmount : String,
    @SerializedName("USAGE_DAY")
    val usageDay : String,
    @SerializedName("USAGE_DAY_WEEK_AND_TIME")
    val usageDayWeekAndTime : String
)