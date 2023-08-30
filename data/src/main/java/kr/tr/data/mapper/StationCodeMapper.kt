package kr.tr.data.mapper

import kr.tr.data.model.item.ResponseBusanSubWayItem
import kr.tr.data.model.item.ResponseSubWayItemData
import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.model.item.SubWayItemData
import retrofit2.Response

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:29
 */
fun Response<ResponseBusanSubWayItem>.asSubWay() : Response<BusanSubWayItem> {
    return Response.success(body()?.let {
        BusanSubWayItem(
            data = it.data?.map { it.asSubWay() } as List<SubWayItemData>,
            totalCount = it.totalCount,
            currentCount = it.currentCount,
            matchCount = it.matchCount,
            page =  it.page,
            perPage = it.perPage
        )
    })

}

fun ResponseSubWayItemData.asSubWay() : SubWayItemData? {
    return SubWayItemData(
        routeName,
        routeNumber,
        dataReferenceDate,
        longitude,
        stationNumber,
        stationStreetAddress,
        stationName,
        stationCallNumber,
        latitude,
        englishStationName,
        operatorName,
        chineseStationName,
        transitLineName,
        transferLineNumber,
        transferStationClassification

    )
}