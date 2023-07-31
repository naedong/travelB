package kr.tr.data.model.item

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kr.tr.data.model.ResponseHeader

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:45
*/
@Keep
data class ResponsGetBusanCultreExhibitDetail(
    @SerializedName("getBusanCultureExhibitDetail")
    val getBusanCultreExhibitDetail: ResponseBusanCultureExhibitDetail
)


@Keep
data class ResponseBusanCultureExhibitDetail(
    @SerializedName("header")
    val header : ResponseHeader,
    @SerializedName("item")
    val item : List<ResponseBusanCultureExhibitDetailItem>,
    @SerializedName("numOfRows")
    val numOfRows : Int,
    @SerializedName("pageNo")
    val pageNo : Int,
    @SerializedName("totalCount")
    val totalCount : Int,
)
@Keep
data class ResponseBusanCultureExhibitDetailItem(
    @SerializedName("res_no")
    val resNo : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("op_st_dt")
    val opStDt : String,
    @SerializedName("op_ed_dt")
    val opEdDt : String,
    @SerializedName("op_at")
    val opAt : String,
    @SerializedName("place_id")
    val placeId : String,
    @SerializedName("place_nm")
    val placeNm : String,
    @SerializedName("theme")
    val theme : String,
    @SerializedName("showtime")
    val showtime : String,
    @SerializedName("rating")
    val rating : String,
    @SerializedName("price")
    val price : String,
    @SerializedName("original")
    val original : String,
    @SerializedName("crew")
    val crew : String,
    @SerializedName("enterprise")
    val enterprise : String,
    @SerializedName("avg_star")
    val avgStar : Double,
    @SerializedName("dabom_url")
    val dabomUrl : String,
)

