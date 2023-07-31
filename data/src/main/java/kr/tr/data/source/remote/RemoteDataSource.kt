package kr.tr.data.source.remote


import android.util.Log
import kr.tr.data.api.BusanApiService
import kr.tr.data.mapper.asBusan
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.model.item.FestivalService
import kr.tr.domain.model.item.GetBusanCultreExhibitDetail
import kr.tr.domain.model.item.GetFestivalKr
import kr.tr.domain.model.item.GetFestivalKrItem
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오전 10:41
 */
class RemoteDataSource @Inject constructor(
    private val busanApiService: BusanApiService
)
{
    suspend fun getBusanCultureExhibitDetail(page : Int) : Response<GetBusanCultreExhibitDetail> {
        return busanApiService.getBusanCultureExhibitDetail(pageNo = page, numOfRows = 10).asBusan()
    }

    suspend fun getBusanFestivalServiceKr(page : Int) : Response<FestivalService> {

        Log.e("travelB", "Remote")
        Log.e("youSSS", "" +
                "No AsBusan" +
                "${busanApiService.getFestivalKr(pageNo = page, numOfRows = 10)}")
        Log.e("travelB", "페스티발 AS BUSAN ${busanApiService.getFestivalKr(pageNo = page, numOfRows = 10).asBusan()}")


        return busanApiService.getFestivalKr(pageNo = page, numOfRows = 10).asBusan()
    }



}