package com.alejandrorios.condorsports.service.network

import com.alejandrorios.condorsports.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProviderImpl {

    companion object {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}