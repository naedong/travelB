package kr.tr.domain.model.item

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:08
 */
data class BusanSubWayItem(
    val currentCount: Int,
    val data: List<SubWayItemData>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)



data class SubWayItemData(
    val routeName: String?,
    val routeNumber: String?,
    val dataReferenceDate: String?,
    val longitude: String?,
    val stationNumber : String?,
    val stationStreetAddress: String?,
    val stationName : String?,
    val stationCallNumber: String?,
    val latitude: String?,
    val englishStationName: String?,
    val operatorName : String?,
    val chineseStationName : String?,
    val transitLineName : String?,
    val transferLineNumber : String?,
    val transferStationClassification : String?
)