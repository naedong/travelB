package kr.tr.data.model.item

import com.google.gson.annotations.SerializedName
import kr.tr.data.model.ResponseHeader

data class ResponseAttractionServiceItem(
    @SerializedName("getAttractionKr")
    val getAttractionKr: ResponseGetAttractionKr
)

data class ResponseGetAttractionKr(
    @SerializedName("header")
    val header: ResponseHeader,
    @SerializedName("item")
    val item: List<ResponseGetAttractionKrItem>,
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)

data class ResponseGetAttractionKrItem(
    @SerializedName("ADDR1")
    val addr1: String,
    @SerializedName("CNTCT_TEL")
    val cntctTel: String,
    @SerializedName("GUGUN_NM")
    val gugunNm: String,
    @SerializedName("HLDY_INFO")
    val hldyInfo: String,
    @SerializedName("HOMEPAGE_URL")
    val homepageUrl: String,
    @SerializedName("ITEMCNTNTS")
    val itemcntnts: String,
    @SerializedName("LAT")
    val lat: Double,
    @SerializedName("LNG")
    val lng: Double,
    @SerializedName("MAIN_IMG_NORMAL")
    val mainImgNormal: String,
    @SerializedName("MAIN_IMG_THUMB")
    val mainImgThumb: String,
    @SerializedName("MAIN_TITLE")
    val mainTitle: String,
    @SerializedName("MIDDLE_SIZE_RM1")
    val middelSizeRm1: String,
    @SerializedName("PLACE")
    val place: String,
    @SerializedName("SUBTITLE")
    val subtitle: String,
    @SerializedName("TITLE")
    val title: String,
    @SerializedName("TRFC_INFO")
    val trfcInfo: String,
    @SerializedName("UC_SEQ")
    val ucSeq: Int,
    @SerializedName("USAGE_AMOUNT")
    val usageAmount: String,
    @SerializedName("USAGE_DAY")
    val usageDay: String,
    @SerializedName("USAGE_DAY_WEEK_AND_TIME")
    val usageDayWeekAndTime: String
)