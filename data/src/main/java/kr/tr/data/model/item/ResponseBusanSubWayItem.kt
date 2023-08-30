package kr.tr.data.model.item

import com.google.gson.annotations.SerializedName

data class ResponseBusanSubWayItem(
    @SerializedName("currentCount")
    val currentCount: Int,
    @SerializedName("data")
    val data: List<ResponseSubWayItemData>,
    @SerializedName("matchCount")
    val matchCount: Int,
    @SerializedName("page")
    val page: Int,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)



data class ResponseSubWayItemData(
    @SerializedName("노선명")
    val routeName: String,
    @SerializedName("노선번호")
    val routeNumber: String,
    @SerializedName("데이터기준일자")
    val dataReferenceDate: String,
    @SerializedName("역경도")
    val longitude: String,
    @SerializedName("역번호")
    val stationNumber : String,
    @SerializedName("역사도로명주소")
    val stationStreetAddress: String,
    @SerializedName("역사명")
    val stationName : String,
    @SerializedName("역사전화번호")
    val stationCallNumber: String,
    @SerializedName("역위도")
    val latitude: String,
    @SerializedName("영문역사명")
    val englishStationName: String,
    @SerializedName("운영기관명")
    val operatorName : String,
    @SerializedName("한자역사명")
    val chineseStationName : String,
    @SerializedName("환승노선명")
    val transitLineName : String,
    @SerializedName("환승노선번호")
    val transferLineNumber : String,
    @SerializedName("환승역구분")
    val transferStationClassification : String
)