package kr.tr.data.model.item

import com.google.gson.annotations.SerializedName
import kr.tr.data.model.ResponseTourismHeader

data class ResponseTourismCode(
    @SerializedName("response")
    val response: ResponseTourism
)

data class ResponseTourism(
    @SerializedName("body")
    val body: ResponseTourismCodeBody,
    @SerializedName("header")
    val header: ResponseTourismHeader
)

data class ResponseTourismCodeBody(
    @SerializedName("items")
    val items: ResponseTourismCodeItems,
    @SerializedName("numOfRows")
    val numOfRows: Int,
    @SerializedName("pageNo")
    val pageNo: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)
data class ResponseTourismCodeItems(
    @SerializedName("item")
    val item: List<ResponseTourismCodeItem>
)

data class ResponseTourismCodeItem(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rnum")
    val rnum: Int
)