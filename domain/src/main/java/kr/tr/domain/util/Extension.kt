package kr.tr.domain.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-03
 * Time: 오후 4:35
 */
fun <T : Any> DataStore<Preferences>.getValueFlow(
    key: Preferences.Key<T>,
    defValue: T,
): Flow<T> {
    return data.catch {
        emit(emptyPreferences())
    }.map { preferences ->
        preferences[key] ?: defValue
    }
}

suspend fun <T : Any> DataStore<Preferences>.set(key: Preferences.Key<T>, value: T) {
    edit {
        it[key] = value
    }
}