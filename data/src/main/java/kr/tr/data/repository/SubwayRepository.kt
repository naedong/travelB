package kr.tr.data.repository

import kr.tr.data.source.remote.SubWayRemoteDataSource
import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.usecase.gateway.SubWayGateWay
import retrofit2.Response
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:41
 */
class SubwayRepository @Inject constructor(
    private val subWayRemoteDataSource: SubWayRemoteDataSource
) : SubWayGateWay {
    override suspend fun getStationCodeGateWay(): Response<BusanSubWayItem> {
        return subWayRemoteDataSource.getSubWayCode()
    }
}