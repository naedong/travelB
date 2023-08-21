package kr.tr.domain.model.item

import kr.tr.domain.model.TourismHeader

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 2:35
 */


data class TourismCode(
    val response: Tourism
)

data class Tourism(
    val body: TourismCodeBody,
    val header: TourismHeader
)

data class TourismCodeBody(
    val items: TourismCodeItems,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)
data class TourismCodeItems(
    val item: List<TourismCodeItem>
)

data class TourismCodeItem(
    val code: String,
    val name: String,
    val rnum: Int
)