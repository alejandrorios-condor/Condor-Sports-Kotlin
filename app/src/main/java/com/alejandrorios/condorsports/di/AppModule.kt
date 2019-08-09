package com.alejandrorios.condorsports.di

import com.alejandrorios.condorsports.BuildConfig
import com.cebroker.domain.models.Config
import com.cebroker.domain.models.CoroutinesContextProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 2019-07-23
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideConfig(): Config = Config(
        BuildConfig.SERVER_URL
    )

    @Singleton
    @Provides
    fun provideContextProvider(): CoroutinesContextProvider {
        return CoroutinesContextProvider(Dispatchers.Main, Dispatchers.IO)
    }
}
