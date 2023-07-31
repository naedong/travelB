package kr.tr.data.mapper

import android.util.Log
import kr.tr.data.model.ResponseHeader
import kr.tr.data.model.asBusan
import kr.tr.data.model.item.ResponseBusanCultureExhibitDetailItem
import kr.tr.data.model.item.ResponseFestivalService
import kr.tr.data.model.item.ResponseGetFestivalKrItem
import kr.tr.domain.model.Header
import kr.tr.domain.model.item.BusanCultureExhibitDetail
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.model.item.GetBusanCultreExhibitDetail
import kr.tr.domain.model.item.GetFestivalKr
import kr.tr.domain.model.item.GetFestivalKrItem
import retrofit2.Response

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-28
 * Time: 오후 4:52
 */
fun Response<ResponseFestivalService>.asBusan() : Response<FestivalService>{

    return Response.success(body()?.let {

        Log.e("travelB", "MaPPer")
        Log.e("travelB", "$it")
        Log.e("travelB", "==============================" +
                "" +
                "ITEM" +
                "" +
                "")
        Log.e("travelB", "${it.getFestivalKr.item.get(0)}")

        Log.e("travelB", "==============================" +
                "\n" +
                "MAP" +
                "\n" +
                "\n")

        it.getFestivalKr.item?.map { id ->
        Log.e("travelB", "$id")
            Log.e("travelB", "==============================")
            Log.e("travelB", "${id.asBusan()}")
        }



        FestivalService(
            GetFestivalKr(
                item = it.getFestivalKr.item?.map { item -> item.asBusan() } as List<GetFestivalKrItem>,
                header = it.getFestivalKr.header?.asBusan() as Header,
                pageNo = it.getFestivalKr.pageNo,
                numOfRows = it.getFestivalKr.numOfRows,
                totalCount = it.getFestivalKr.totalCount
            )
        )
    })
      }


fun ResponseGetFestivalKrItem.asBusan(): GetFestivalKrItem {
    return GetFestivalKrItem(
        addr1 = addr1,
        addr2 = addr2,
        cntctTel = cntctTel,
        gugunNm = gugunNm,
        homepageUrl = homepageUrl,
        itemcntnts = itemcntnts,
        lat = lat,
        lng = lng,
        mainImgNormal = mainImgNormal,
        mainImgThumb = mainImgThumb,
        mainPlace = mainPlace,
        mainTitle = mainTitle,
        middelSizeRm1 = middelSizeRm1,
        place = place,
        subtitle = subtitle,
        title = title,
        trfcInfo = trfcInfo,
        ucSeq =ucSeq,
        usageAmount = usageAmount,
        usageDay = usageDay,
        usageDayWeekAndTime = usageDayWeekAndTime
    )
}