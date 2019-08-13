package com.alejandrorios.condorsports.data.network

import retrofit2.Response

interface RetrofitClient {
    suspend fun <T : Any> apiCall(call: Response<T>): Response<T>
}
