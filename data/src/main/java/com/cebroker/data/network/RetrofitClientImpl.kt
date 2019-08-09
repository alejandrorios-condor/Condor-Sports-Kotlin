package com.cebroker.data.network

import retrofit2.Response

class RetrofitClientImpl : RetrofitClient {

    override suspend fun <T : Any> apiCall(call: Response<T>): Response<T> {

        if (call.isSuccessful) {
            return call
        }

        throw Throwable(call.errorBody()?.string())
    }
}
