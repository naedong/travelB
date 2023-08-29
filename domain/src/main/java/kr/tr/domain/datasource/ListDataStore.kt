package kr.tr.domain.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.tr.domain.model.item.AreaBasedListItem
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import kr.tr.domain.util.getValueFlow
import kr.tr.domain.util.set
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-23
 * Time: 오전 11:42
 */
@Singleton
class ListDataStore @Inject constructor(
    @ApplicationContext context: Context
){

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)
    private val dataStore = context.dataStore

    suspend fun setAreaBasedListItem(areaBasedListItem: AreaBasedListItem){
        dataStore.edit {
            it[listPreferenceKeys.listKey] = Json.encodeToString<AreaBasedListItem>(areaBasedListItem)
        }
    }

    fun getAreaBaseListItem() : Flow<AreaBasedListItem> {
        return dataStore.getValueFlow( listPreferenceKeys.listKey,
            defValue = listPreferenceKeys.listKey.name
        ).map {
            Json.decodeFromString<AreaBasedListItem>(it)
        }
    }

    companion object {
        private const val PREF_NAME = "data_store"
        private const val LIST_NAME_KEY = "LIST_NAME_KEY"
        private const val LIST_KEY = "LIST_KEY"
    }

    object listPreferenceKeys {
        val listKey = stringPreferencesKey(LIST_KEY)
        val listNameKey = stringPreferencesKey(LIST_NAME_KEY)
    }


}