package kr.tr.data.repository.local

import kotlinx.coroutines.flow.Flow
import kr.tr.domain.datasource.ListDataStore
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.repository.local.ListDataStoreRepository
import javax.inject.Inject

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-23
 * Time: 오후 12:06
 */
class ListDataStoreRepositoryImpl @Inject constructor(
    private val listDataStore : ListDataStore
) : ListDataStoreRepository {

    override fun getAreaBasedListItemDataStore(): Flow<AreaBasedListItem> {
        return listDataStore.getAreaBaseListItem()
    }

    override suspend fun setAreaBasedListItemDataStore(area: AreaBasedListItem) {
        listDataStore.setAreaBasedListItem(area)
    }
}