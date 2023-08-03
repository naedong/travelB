package kr.tr.domain.datasource

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kr.tr.commom.expection.Failure
import kr.tr.domain.model.item.GetAttractionKrItem
import kr.tr.domain.usecase.AttrationServiceUseCase
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 3:38
 */
class AttrationServiceDataSource @Inject constructor(
    private val useCase: AttrationServiceUseCase
) : PagingSource<Int, GetAttractionKrItem>() {

    override fun getRefreshKey(state: PagingState<Int, GetAttractionKrItem>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetAttractionKrItem> {
        try {
            val page = params.key ?: 1
            val response = useCase.invoke(page)
            response.body()?.getAttractionKr?.header?.message.let {
                if (it != "NORMAL_CODE") {
                    return LoadResult.Error(throw Exception("Error You need Check Response"))
                }
            }
            val list = response.body()?.getAttractionKr?.item ?: emptyList()

            return LoadResult.Page(
                data = list, nextKey = page.plus(1),
                prevKey = when (page) {
                    1 -> null
                    else -> page - 1
                }
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