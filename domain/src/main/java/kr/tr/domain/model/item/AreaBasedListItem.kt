package kr.tr.domain.model.item

import kr.tr.domain.model.TourismHeader

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-21
 * Time: 오후 4:29
 */

data class AreaBasedItem(
    val response: AreaBasedItemResponse
)

data class AreaBasedItemResponse(
    val body: AreaBasedItemBody,
    val header: TourismHeader
)

data class AreaBasedItemBody(
    val items: AreaBasedListItems,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class AreaBasedListItems(
    val item: List<AreaBasedListItem>?
)

data class AreaBasedListItem(
    val addr1: String?,
    val addr2: String?,
    val areacode: String?,
    val booktour: String?,
    val cat1: String?,
    val cat2: String?,
    val cat3: String?,
    val contentid: String?,
    val contenttypeid: String?,
    val cpyrhtDivCd: String?,
    val createdtime: String?,
    val firstimage: String?,
    val firstimage2: String?,
    val mapx: String?,
    val mapy: String?,
    val mlevel: String?,
    val modifiedtime: String?,
    val sigungucode: String?,
    val tel: String?,
    val title: String?,
    val zipcode: String?
)