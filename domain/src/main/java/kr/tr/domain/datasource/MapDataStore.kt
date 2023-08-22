package kr.tr.domain.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kr.tr.domain.model.item.CurrentLocationTrackingModel
import kr.tr.domain.util.getValueFlow
import kr.tr.domain.util.set
import net.daum.mf.map.api.MapView.CurrentLocationTrackingMode

import javax.inject.Inject
import javax.inject.Singleton

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-09
 * Time: 오전 11:13
 */


@Singleton
class MapDataStore @Inject constructor(
    @ApplicationContext context: Context
){

    object preferenceKeys {
        val locationKey = stringPreferencesKey(LOCATION_KEY)
        val firstKey = booleanPreferencesKey(FIRST_KEY)
    }


    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)
    private val dataStore = context.dataStore

    fun getTrackingMode() : Flow<CurrentLocationTrackingModel> {
        return dataStore.getValueFlow(
            stringPreferencesKey(TRACKING_MODE), CurrentLocationTrackingModel.TrackingModeOff.name
        ).map {
            CurrentLocationTrackingModel.valueOf(it)
        }
    }

    suspend fun setTrackingModel(trackingMode: CurrentLocationTrackingModel){
         dataStore.set(stringPreferencesKey(TRACKING_MODE),  trackingMode.name)
    }


    suspend fun isPutBoolean(check : Boolean){
        dataStore.edit { preferences ->
            preferences[preferenceKeys.firstKey] == check
        }
    }

    val isGetBoolean : Flow<Boolean> = dataStore.data
        .catch { e ->
            if( e is IOException) emit(emptyPreferences())
            else
                throw e
        }
        .map {  preferences ->
            preferences[preferenceKeys.firstKey] ?: false

        }

    companion object {

        private const val PREF_NAME = "data_store"
        private const val TRACKING_MODE = "TRACKING_MODE"
        private const val FIRST_KEY = "FIRST_KEY"
        private const val LOCATION_KEY = "LOCATION_KEY"

    }
}