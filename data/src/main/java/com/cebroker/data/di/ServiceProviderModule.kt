package com.cebroker.data.di

import android.content.Context
import com.cebroker.data.helpers.RealmStorageHelper
import com.cebroker.data.network.api.TeamsService
import com.cebroker.data.utils.BASE_OK_HTTP_CLIENT
import com.cebroker.data.utils.BASE_RETROFIT_COROUTINES
import com.cebroker.data.utils.TIMEOUT_SECONDS
import com.cebroker.domain.models.Config
import dagger.Module
import dagger.Provides
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 2019-07-22
 */
@Module(includes = [HelperModule::class])
class ServiceProviderModule {

    @Provides
    @Singleton
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    @Named(BASE_OK_HTTP_CLIENT)
    @Provides
    @Singleton
    fun provideBaseClient(
        loggingInterceptor: HttpLoggingInterceptor
    ):
            OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Named(BASE_RETROFIT_COROUTINES)
    @Provides
    @Singleton
    fun provideBaseRetrofitCoroutines(
        @Named(BASE_OK_HTTP_CLIENT) baseOkHttpClient: OkHttpClient,
        config: Config
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(config.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(baseOkHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTeamsService(
        @Named(BASE_RETROFIT_COROUTINES) retrofit: Retrofit
    ): TeamsService {
        return retrofit.create(TeamsService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideRealmService(
//         context: Context
//    ): RealmStorageHelper {
//        return RealmStorageHelper(context)
//    }
}
