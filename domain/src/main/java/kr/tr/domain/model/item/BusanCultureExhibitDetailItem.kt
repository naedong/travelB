package kr.tr.domain.model.item

import kr.tr.domain.model.Header

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:51
 */
data class BusanCultureExhibitDetailItem(
    val avgStar: Double,
    val crew: String,
    val dabomUrl: String,
    val enterprise: String,
    val opAt: String,
    val opEdDt: String,
    val opStDt: String,
    val original: String,
    val placeId: String,
    val placeNm: String,
    val price: String,
    val rating: String,
    val resNo: String,
    val showtime: String,
    val theme: String,
    val title: String
)

data class BusanCultureExhibitDetail(
    val header: Header,
    val item: List<BusanCultureExhibitDetailItem>,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class GetBusanCultreExhibitDetail(
    val getBusanCultureExhibitDetail: BusanCultureExhibitDetail
)