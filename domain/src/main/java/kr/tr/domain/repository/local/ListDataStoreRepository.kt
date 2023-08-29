package kr.tr.domain.repository.local

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.model.item.AreaBasedListItem

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-23
 * Time: 오후 12:11
 */
interface ListDataStoreRepository {
    fun getAreaBasedListItemDataStore(): Flow<AreaBasedListItem>
    suspend fun setAreaBasedListItemDataStore(area: AreaBasedListItem)
}