package kr.com.map.data.model

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-11
 * Time: 오전 12:22
 */
data class DocumentResult(
    val id: String,
    val placeName: String,
    val categoryName: String,
    val categoryGroupCode: String,
    val categoryGroupName: String,
    val phone: String,
    val addressName: String,
    val roadAddressName: String,
    val x: String,
    val y: String,
    val placeUrl: String,
    val distance: String,
    val addressType: String,
    val address: Address,
    val roadAddress: RoadAddress,
)

data class DocumentItemResult(val documentList: List<Document>, val isMoveCamera: Boolean) {
    companion object {
        fun empty() = DocumentItemResult(documentList = emptyList(), isMoveCamera = false)
    }
}
