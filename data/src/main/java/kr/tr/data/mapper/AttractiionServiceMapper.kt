package kr.tr.data.mapper

import kr.tr.commom.utill.DataPreprocessing
import kr.tr.data.model.asBusan
import kr.tr.data.model.item.ResponseAttractionServiceItem
import kr.tr.data.model.item.ResponseGetAttractionKrItem
import kr.tr.domain.model.Header
import kr.tr.domain.model.item.AttractionServiceItem
import kr.tr.domain.model.item.GetAttractionKr
import kr.tr.domain.model.item.GetAttractionKrItem
import retrofit2.Response


/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 3:23
 */
fun Response<ResponseAttractionServiceItem>.asBusan() : Response<AttractionServiceItem> {

    return Response.success(body()?.let {
        AttractionServiceItem(
            GetAttractionKr(
                item = it.getAttractionKr.item?.map { item -> item.asBusan() } as List<GetAttractionKrItem>,
                header = it.getAttractionKr.header?.asBusan() as Header,
                pageNo = it.getAttractionKr.pageNo,
                numOfRows = it.getAttractionKr.numOfRows,
                totalCount = it.getAttractionKr.totalCount
            )
        )
    })
}


fun ResponseGetAttractionKrItem.asBusan(): GetAttractionKrItem {
    return GetAttractionKrItem(
        addr1 =  addr1,
        hldyInfo = hldyInfo,
        cntctTel = cntctTel,
        gugunNm = gugunNm,
        homepageUrl = homepageUrl,
        itemcntnts = DataPreprocessing(itemcntnts),
        lat = lat,
        lng = lng,
        mainImgNormal = mainImgNormal,
        mainImgThumb = mainImgThumb,
        mainTitle = mainTitle,
        middelSizeRm1 = middelSizeRm1,
        place = place,
        subtitle = subtitle,
        title = title,
        trfcInfo = DataPreprocessing(trfcInfo),
        ucSeq =ucSeq,
        usageAmount = usageAmount,
        usageDay = usageDay,
        usageDayWeekAndTime = usageDayWeekAndTime,
    )
}

