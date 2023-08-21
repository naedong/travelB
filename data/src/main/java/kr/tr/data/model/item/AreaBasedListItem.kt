package kr.tr.data.model.item

import com.google.gson.annotations.SerializedName
import kr.tr.data.model.ResponseTourismHeader

data class ResponseAreaBasedItem(
    @SerializedName("response")
    val response: ResponseAreaBasedListResponse
)

data class ResponseAreaBasedListResponse(
    @SerializedName("body")
    val body: ResponseAreaBasedListBody,
    @SerializedName("header")
    val header: ResponseTourismHeader
)

data class ResponseAreaBasedListBody(
    @SerializedName("items")
    val items: ResponseAreaBasedListItems,
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)

data class ResponseAreaBasedListItems(
    @SerializedName("item")
    val item: List<ResponseAreaBasedListItem>?
)

data class ResponseAreaBasedListItem(
    @SerializedName("addr1")
    val addr1: String?,
    @SerializedName("addr2")
    val addr2: String?,
    @SerializedName("areacode")
    val areacode: String?,
    @SerializedName("booktour")
    val booktour: String?,
    @SerializedName("cat1")
    val cat1: String?,
    @SerializedName("cat2")
    val cat2: String?,
    @SerializedName("cat3")
    val cat3: String?,
    @SerializedName("contentid")
    val contentid: String?,
    @SerializedName("contenttypeid")
    val contenttypeid: String?,
    @SerializedName("cpyrhtDivCd")
    val cpyrhtDivCd: String?,
    @SerializedName("createdtime")
    val createdtime: String?,
    @SerializedName("firstimage")
    val firstimage: String?,
    @SerializedName("firstimage2")
    val firstimage2: String?,
    @SerializedName("mapx")
    val mapx: String?,
    @SerializedName("mapy")
    val mapy: String?,
    @SerializedName("mlevel")
    val mlevel: String?,
    @SerializedName("modifiedtime")
    val modifiedtime: String?,
    @SerializedName("sigungucode")
    val sigungucode: String?,
    @SerializedName("tel")
    val tel: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("zipcode")
    val zipcode: String?
)