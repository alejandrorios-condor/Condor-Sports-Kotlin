package com.cebroker.data.network

import retrofit2.Response

class RetrofitClientImpl : RetrofitClient {

    @Throws(NullPointerException::class)
    override suspend fun <T : Any> apiCall(call: Response<T>): Response<T> {

        if (call.isSuccessful) {
            return call
        }

        throw NullPointerException()
    }
}