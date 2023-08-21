package kr.tr.data.mapper

import kr.tr.data.model.asTourism
import kr.tr.data.model.item.ResponseAreaBasedItem
import kr.tr.data.model.item.ResponseAreaBasedListBody
import kr.tr.data.model.item.ResponseAreaBasedListItem
import kr.tr.data.model.item.ResponseAreaBasedListItems
import kr.tr.data.model.item.ResponseAreaBasedListResponse
import kr.tr.data.model.item.ResponseTourism
import kr.tr.data.model.item.ResponseTourismCode
import kr.tr.data.model.item.ResponseTourismCodeBody
import kr.tr.data.model.item.ResponseTourismCodeItem
import kr.tr.data.model.item.ResponseTourismCodeItems
import kr.tr.domain.model.TourismHeader
import kr.tr.domain.model.item.AreaBasedItem
import kr.tr.domain.model.item.AreaBasedItemBody
import kr.tr.domain.model.item.AreaBasedItemResponse
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.model.item.AreaBasedListItems
import kr.tr.domain.model.item.Tourism
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.model.item.TourismCodeBody
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.model.item.TourismCodeItems
import retrofit2.Response

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-21
 * Time: 오후 4:36
 */
fun Response<ResponseAreaBasedItem>.asTourism() : Response<AreaBasedItem> {
    return Response.success(body()?.let {
        AreaBasedItem(
            it.response?.asTourism() as AreaBasedItemResponse
        )
    })

}

fun ResponseAreaBasedListResponse.asTourism() : AreaBasedItemResponse? {
    return  AreaBasedItemResponse(
        body?.asTourism() as AreaBasedItemBody,
        header.asTourism() as TourismHeader
    )
}


fun ResponseAreaBasedListBody.asTourism() : AreaBasedItemBody? {
    return AreaBasedItemBody(
        items?.asTourism() as AreaBasedListItems,
        numOfRows,
        pageNo,
        totalCount

    )
}



fun ResponseAreaBasedListItems.asTourism() : AreaBasedListItems? {
    return AreaBasedListItems(
        item?.map { it.asTourism() } as List<AreaBasedListItem>
    )
}


fun ResponseAreaBasedListItem.asTourism() : AreaBasedListItem? {
    return AreaBasedListItem(
        addr1,
        addr2,
        areacode,
        booktour,
        cat1,
        cat2,
        cat3,
        contentid,
        contenttypeid,
        cpyrhtDivCd,
        createdtime,
        firstimage,
        firstimage2,
        mapx,
        mapy,
        mlevel,
        modifiedtime,
        sigungucode,
        tel,
        title,
        zipcode
    )

}

