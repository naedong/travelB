package kr.tr.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.TourismCodeItem
import kr.tr.domain.model.item.TourismCodeItems
import kr.tr.domain.usecase.gateway.TourismGateWay
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-14
 * Time: 오후 3:34
 */

fun interface TourismRepositoryInter : () -> Flow<PagingData<TourismCodeItem>>