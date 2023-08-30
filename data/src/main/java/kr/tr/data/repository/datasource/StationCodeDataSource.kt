package kr.tr.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.commom.expection.Failure
import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.model.item.SubWayItemData
import kr.tr.domain.usecase.StationCodeUseCase
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:16
 */
class StationCodeDataSource @Inject constructor(
    val stationCode : StationCodeUseCase
) : PagingSource<Int, SubWayItemData>(){
    override fun getRefreshKey(state: PagingState<Int, SubWayItemData>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SubWayItemData> {
        try {
            val nextPage = params.key ?: 1

            val responseStationCode = stationCode.invoke()

            if (responseStationCode.body()?.data?.isNullOrEmpty() == true) {
                return LoadResult.Error(throw Exception("Null or Empty resultMsg"))
            }

            val list = responseStationCode.body()?.data ?: emptyList()

            if(nextPage != 1){
                return LoadResult.Error(throw Exception("Too Many Pass"))

            }


            return LoadResult.Page(
                data = list,
                nextKey = nextPage.plus(1),
                prevKey = if (nextPage == 1) null
                else nextPage.minus(1)
            )
        }
        catch (e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    return LoadResult.Error(
                        Failure.NoInternet(e.message)
                    )
                }
                is TimeoutException -> {
                    return LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }
                else -> {
                    return LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
                }
            }
        }
    }
}