package kr.tr.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.commom.expection.Failure
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.usecase.AreaBasedItemUseCase
import kr.tr.domain.usecase.TourismCodeUseCase
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-21
 * Time: 오후 4:58
 */
class AreaBaseDataSource @Inject constructor(
    private val useCase: AreaBasedItemUseCase,
    private val sigunguCode : Int
) : PagingSource<Int, AreaBasedListItem>()  {

    override fun getRefreshKey(state: PagingState<Int, AreaBasedListItem>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AreaBasedListItem> {
        try {
            val nextPage = params.key ?: 1

            val responseTourismCode = useCase.invoke(sigunguCode = sigunguCode, pageNo = nextPage)

            if (responseTourismCode.body()?.response?.header?.resultMsg?.isNullOrEmpty() == true) {
                return LoadResult.Error(throw Exception("Null or Empty resultMsg"))
            }

            val list = responseTourismCode.body()?.response?.body?.items?.item ?: emptyList()

            if (nextPage != 1) {
                return LoadResult.Error(throw Exception("Too Many Pass"))

            }


            return LoadResult.Page(
                data = list,
                nextKey = nextPage.plus(1),
                prevKey = if (nextPage == 1) null
                else nextPage.minus(1)
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