package kr.tr.domain.usecase

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.AreaBasedListItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-23
 * Time: 오후 12:16
 */


fun interface getAreaBasedListItemDataStoreUseCase : () -> Flow<AreaBasedListItem>

fun interface setAreaBasedListItemDataStoreUseCase {
    suspend fun excute(area : AreaBasedListItem)
}