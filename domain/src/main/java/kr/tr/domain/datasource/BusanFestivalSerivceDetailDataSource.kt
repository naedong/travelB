package kr.tr.domain.datasource

import android.util.Log
import androidx.paging.PagingSource
import kr.tr.commom.expection.Failure
import kr.tr.domain.model.item.GetFestivalKrItem
import kr.tr.domain.usecase.FestivalServiceUseCase
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-01
 * Time: 오후 12:12
 */
class BusanFestivalServiceDetailDataSource @Inject constructor(
    private val festivalServiceUseCase: FestivalServiceUseCase
) {
     suspend fun getFestivalServiceDetail(ucSeq: Int): GetFestivalKrItem? {
        try {
            val responseItem = festivalServiceUseCase.getFestivlaServiceDetailGateWay(ucSeq)
            if (responseItem.body()?.getFestivalKr?.header?.message?.isNullOrEmpty() == true) {
                return throw Exception("Error")
            }
            val list = responseItem.body()?.getFestivalKr?.item ?: emptyList()
            return list.get(0)
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException, is SocketTimeoutException -> {
                    Error(
                        Failure.NoInternet(e.message)
                    )
                    Log.e("UnknownHostException or SocketTimeoutException Error", "${e.message}")
                    return null
                }

                is TimeoutException -> {
                    Error(
                        Failure.Timeout(e.message)
                    )

                    Log.e("TimeoutException Error", "${e.message}")
                    return null
                }

                else -> {
                    Error(
                        Failure.Unknown(e.message)
                    )

                    Log.e("Error", "${e.message}")
                    return null
                }
            }

        }
    }

}