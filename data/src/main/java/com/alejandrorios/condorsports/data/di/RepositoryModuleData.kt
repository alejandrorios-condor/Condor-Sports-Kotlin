package com.alejandrorios.condorsports.data.di

import com.alejandrorios.condorsports.data.network.RetrofitClient
import com.alejandrorios.condorsports.data.network.api.TeamsService
import com.alejandrorios.condorsports.data.repository.RealmStorageRepositoryImpl
import com.alejandrorios.condorsports.data.repository.TeamRepositoryImpl
import com.alejandrorios.condorsports.domain.helpers.IRealmStorageHelper
import com.alejandrorios.condorsports.domain.repositories.RealmStorageRepository
import com.alejandrorios.condorsports.domain.repositories.TeamsRepository
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
