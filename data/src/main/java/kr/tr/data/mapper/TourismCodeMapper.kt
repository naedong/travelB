package kr.tr.data.mapper

import com.google.gson.annotations.SerializedName
import kr.tr.data.model.ResponseTourismHeader
import kr.tr.data.model.asTourism
import kr.tr.data.model.item.ResponseTourism
import kr.tr.data.model.item.ResponseTourismCode
import kr.tr.data.model.item.ResponseTourismCodeBody
import kr.tr.data.model.item.ResponseTourismCodeItem
import kr.tr.data.model.item.ResponseTourismCodeItems
import kr.tr.domain.model.TourismHeader
import kr.tr.domain.model.item.Tourism
import kr.tr.domain.model.item.TourismCode
import kr.tr.domain.model.item.TourismCodeBody
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.model.item.TourismCodeItems
import retrofit2.Response

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 2:51
 */

fun Response<ResponseTourismCode>.asTourism() : Response<TourismCode> {
    return Response.success(body()?.let {
        TourismCode(
            it.response?.asTourism() as Tourism
        )
    })

}

fun ResponseTourism.asTourism() : Tourism? {
    return  Tourism(
        body?.asTourism() as TourismCodeBody,
        header.asTourism() as TourismHeader
    )
}


fun ResponseTourismCodeBody.asTourism() : TourismCodeBody? {
    return TourismCodeBody(
        items?.asTourism() as TourismCodeItems,
        numOfRows,
        pageNo,
        totalCount

    )
}



fun ResponseTourismCodeItems.asTourism() : TourismCodeItems? {
   return TourismCodeItems(
      item?.map { it.asTourism() } as List<TourismCodeItem>
   )
}


fun ResponseTourismCodeItem.asTourism() : TourismCodeItem? {
    return TourismCodeItem(
        code,
        name,
        rnum
    )
}


