package com.cebroker.data.di

import com.cebroker.data.network.RetrofitClient
import com.cebroker.data.network.api.TeamsService
import com.cebroker.data.repository.RealmStorageRepositoryImpl
import com.cebroker.data.repository.TeamRepositoryImpl
import com.cebroker.domain.helpers.IRealmStorageHelper
import com.cebroker.domain.repositories.RealmStorageRepository
import com.cebroker.domain.repositories.TeamsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 2019-07-22
 */
@Module(includes = [ServiceProviderModule::class])
class RepositoryModuleData {

    @Provides
    @Singleton
    fun provideTeamsRepository(
        retrofitClient: RetrofitClient,
        teamsService: TeamsService
    ): TeamsRepository {
        return TeamRepositoryImpl(retrofitClient, teamsService)
    }

    @Provides
    @Singleton
    fun provideRealmStorageRepository(realmStorageHelper: IRealmStorageHelper): RealmStorageRepository {
        return RealmStorageRepositoryImpl(realmStorageHelper)
    }
}