package kr.tr.domain.datasource

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.commom.expection.Failure
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.domain.usecase.FestivalServiceUseCase
import kr.tr.domain.usecase.gateway.GateWay
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-07-31
 * Time: 오전 10:25
 */
class BusanFestivalServiceDataSource @Inject constructor(
    private val festivalServiceUseCase: FestivalServiceUseCase
) : PagingSource<Int, GetFestivalKrItem>() {

    override fun getRefreshKey(state: PagingState<Int, GetFestivalKrItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetFestivalKrItem> {
        try {
            val nextPage = params.key ?: 1
            val busanResponse = festivalServiceUseCase.invoke(nextPage)
            if (busanResponse.body()?.getFestivalKr?.header?.message?.isNullOrEmpty() == true) {
                return LoadResult.Error(throw Exception("Error Check Plz"))
            }
            val list = busanResponse.body()?.getFestivalKr?.item ?: emptyList()
            return LoadResult.Page(
                data = list, nextKey = nextPage.plus(1), prevKey = if (nextPage == 1) null
                else nextPage - 1
            )
        } catch (e: Exception) {
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
