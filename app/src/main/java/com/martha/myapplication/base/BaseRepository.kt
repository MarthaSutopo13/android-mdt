package com.martha.myapplication.base

import com.martha.myapplication.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> handlingApiCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return  withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        Resource.Failure(null, throwable.message(), throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(null, null, null, null)
                    }
                }
            }
        }
    }
}