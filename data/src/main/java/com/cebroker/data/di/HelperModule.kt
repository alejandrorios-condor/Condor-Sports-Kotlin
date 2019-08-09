package com.cebroker.data.di

import android.content.Context
import com.cebroker.data.helpers.RealmStorageHelper
import com.cebroker.data.network.RetrofitClient
import com.cebroker.data.network.RetrofitClientImpl
import com.cebroker.domain.helpers.IRealmStorageHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 2019-07-22
 */
@Module
class HelperModule {

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClientImpl()
    }

//    @Provides
//    @Singleton
//    fun provideRealmStorageHelper(context: Context): IRealmStorageHelper =
//        RealmStorageHelper(context)
}
