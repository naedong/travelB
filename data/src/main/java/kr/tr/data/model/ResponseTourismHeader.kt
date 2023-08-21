package kr.tr.data.model

import com.google.gson.annotations.SerializedName
import kr.tr.domain.model.Header
import kr.tr.domain.model.TourismHeader

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 2:26
 */
data class ResponseTourismHeader (
    @SerializedName("resultCode")
    val resultCode : String,
    @SerializedName("resultMsg")
    val resultMsg : String
)

fun ResponseTourismHeader?.asTourism(): TourismHeader? {
    if (this == null) return null
    return TourismHeader(
        resultCode = resultCode,
        resultMsg = resultMsg
    )
}