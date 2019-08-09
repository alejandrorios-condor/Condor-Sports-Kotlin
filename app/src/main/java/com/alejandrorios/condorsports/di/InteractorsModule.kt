package com.alejandrorios.condorsports.di

import com.cebroker.data.di.RepositoryModuleData
import com.cebroker.domain.interactor.*
import com.cebroker.domain.models.Events
import com.cebroker.domain.models.Teams
import com.cebroker.domain.repositories.RealmStorageRepository
import com.cebroker.domain.repositories.TeamsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 2019-07-22
 */
@Module(includes = [RepositoryModuleData::class])
class InteractorsModule {

    @Singleton
    @Provides
    fun provideGetTeamsInteractor(
        teamsRepository: TeamsRepository
    ): Interactor<List<Teams>, String> = GetTeamsInteractor(
        teamsRepository
    )

    @Singleton
    @Provides
    fun provideGetTeamDetailsInteractor(
        teamsRepository: TeamsRepository
    ): Interactor<List<Events>, String> = GetEventsInteractor(
        teamsRepository
    )

//    @Singleton
//    @Provides
//    fun provideSaveTeamsInteractor(
//        realmStorageRepository: RealmStorageRepository
//    ): Interactor<Unit, List<Teams>> = SaveTeamsInteractor(
//        realmStorageRepository
//    )

    @Singleton
    @Provides
    fun provideGetTeamBadgeInteractor(
        realmStorageRepository: RealmStorageRepository
    ): Interactor<String, String> = GetTeamBadgeInteractor(
        realmStorageRepository
    )
}
