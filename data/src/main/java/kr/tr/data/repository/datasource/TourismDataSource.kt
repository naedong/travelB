package kr.tr.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.commom.expection.Failure
import kr.tr.data.source.remote.TourismRemoteDataSource
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.model.item.TourismCodeItems
import kr.tr.domain.usecase.TourismCodeUseCase
import kr.tr.domain.usecase.gateway.GateWay
import kr.tr.domain.usecase.gateway.TourismGateWay
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 3:58
 */
class TourismDataSource @Inject constructor(
    private val useCase: TourismCodeUseCase
) : PagingSource<Int, TourismCodeItem>() {

    override fun getRefreshKey(state: PagingState<Int, TourismCodeItem>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TourismCodeItem> {
        try {
            val nextPage = params.key ?: 1

            val responseTourismCode = useCase.invoke()

            if (responseTourismCode.body()?.response?.header?.resultMsg?.isNullOrEmpty() == true) {
                return LoadResult.Error(throw Exception("Null or Empty resultMsg"))
            }

            val list = responseTourismCode.body()?.response?.body?.items?.item ?: emptyList()

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