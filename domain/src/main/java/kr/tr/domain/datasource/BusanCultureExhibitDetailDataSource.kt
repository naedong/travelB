package kr.tr.domain.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.commom.expection.Failure
import kr.tr.domain.model.item.BusanCultureExhibitDetailItem
import kr.tr.domain.usecase.BusanCultureExhibitDetailUseCase
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by NEXT2023
 * Date: 2023-07-27
 * Time: 오후 1:11
 */
class BusanCultureExhibitDetailDataSource @Inject constructor(
    private val busanCultureExhibitDetailUseCase: BusanCultureExhibitDetailUseCase
) : PagingSource<Int, BusanCultureExhibitDetailItem>() {

    override fun getRefreshKey(state: PagingState<Int, BusanCultureExhibitDetailItem>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BusanCultureExhibitDetailItem> {
        try {
            val nextPage = params.key ?: 1
            val busanResponse = busanCultureExhibitDetailUseCase.invoke(nextPage)

            if (busanResponse.body()?.getBusanCultureExhibitDetail
                    ?.header?.message?.isNullOrEmpty() == true) {
                return LoadResult.Error(throw Exception("Error Check Plz"))
            }
            val list = busanResponse.body()?.getBusanCultureExhibitDetail
                ?.item ?: emptyList()

            return LoadResult.Page(data = list,
                nextKey = nextPage.plus(1),
                prevKey = if (nextPage == 1) null
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
                    return  LoadResult.Error(
                        Failure.Timeout(e.message)
                    )
                }

                else -> {
                  return  LoadResult.Error(
                        Failure.Unknown(e.message)
                    )
                }
            }
        }
    }
}
