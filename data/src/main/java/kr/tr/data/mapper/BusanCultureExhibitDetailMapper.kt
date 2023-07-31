package kr.tr.data.mapper


import kr.tr.data.model.asBusan
import kr.tr.data.model.item.ResponsGetBusanCultreExhibitDetail
import kr.tr.data.model.item.ResponseBusanCultureExhibitDetailItem
import kr.tr.domain.model.Header
import kr.tr.domain.model.item.BusanCultureExhibitDetail
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.model.item.GetBusanCultreExhibitDetail
import retrofit2.Response

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:43
 */

fun Response<ResponsGetBusanCultreExhibitDetail>.asBusan(): Response<GetBusanCultreExhibitDetail> {
    return Response.success(body()?.let {
        GetBusanCultreExhibitDetail(
            BusanCultureExhibitDetail(
                item = it.getBusanCultreExhibitDetail.item?.map { item -> item?.asBusan() } as List<BusanCultureExhibitDetailItem>,
                header = it.getBusanCultreExhibitDetail.header?.asBusan() as Header,
                pageNo = it.getBusanCultreExhibitDetail.pageNo,
                numOfRows = it.getBusanCultreExhibitDetail.numOfRows,
                totalCount = it.getBusanCultreExhibitDetail.totalCount
            )
        )
    })
}



fun ResponseBusanCultureExhibitDetailItem?.asBusan(): BusanCultureExhibitDetailItem? {
    if (this == null) return null
    return BusanCultureExhibitDetailItem(
        resNo = resNo,
        placeNm = placeNm,
        placeId = placeId,
        opStDt = opStDt,
        opEdDt = opEdDt,
        opAt = opAt,
        dabomUrl = dabomUrl,
        avgStar = avgStar,
        title = title,
        theme = theme,
        crew = crew,
        enterprise = enterprise,
        price = price,
        rating = rating,
        showtime = showtime,
        original = original,
    )
}