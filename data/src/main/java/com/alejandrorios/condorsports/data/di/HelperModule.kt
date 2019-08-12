package com.alejandrorios.condorsports.data.di

import android.content.Context
import com.alejandrorios.condorsports.data.helpers.RealmStorageHelper
import com.alejandrorios.condorsports.data.network.RetrofitClient
import com.alejandrorios.condorsports.data.network.RetrofitClientImpl
import com.alejandrorios.condorsports.domain.helpers.IRealmStorageHelper
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
