package kr.tr.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.BusanSubWayItem
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import kr.tr.domain.model.item.SubWayItemData
import kr.tr.domain.model.item.TourismCodeItem
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-28
 * Time: 오전 11:13
 */
fun interface getStationCodeRepositoryInter : () -> Flow<PagingData<SubWayItemData>>