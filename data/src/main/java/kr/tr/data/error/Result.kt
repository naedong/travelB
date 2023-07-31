package kr.tr.data.error

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-06-30
 * Time: 오전 10:25
 */
sealed class Result<out T> {
    data class Success<out T>(val response: T) : Result<T>()
    data class Error(val remoteSourceException: RemoteSourceException) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = this
    .map<T, Result<T>> {
        kr.tr.data.error.Result.Success(it)
    }
    .onStart {
        emit(kr.tr.data.error.Result.Loading)
    }
    .catch {
        emit(kr.tr.data.error.Result.Error(kr.tr.data.error.RequestErrorHandler.getRequestError(it)))
    }
