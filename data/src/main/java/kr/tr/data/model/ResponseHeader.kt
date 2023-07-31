package kr.tr.data.model

import com.google.gson.annotations.SerializedName
import kr.tr.domain.model.Header

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:18
 */
data class ResponseHeader (
    @SerializedName("code")
    val code : String,
    @SerializedName("message")
    val message : String
    )

fun ResponseHeader?.asBusan(): Header? {
    if (this == null) return null
    return Header(
        code = code,
        message = message
    )
}