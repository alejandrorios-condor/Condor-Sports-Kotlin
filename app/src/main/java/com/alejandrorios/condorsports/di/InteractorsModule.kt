package com.alejandrorios.condorsports.di

import com.alejandrorios.condorsports.data.di.RepositoryModuleData
import com.alejandrorios.condorsports.domain.interactor.*
import com.alejandrorios.condorsports.domain.models.Events
import com.alejandrorios.condorsports.domain.models.Teams
import com.alejandrorios.condorsports.domain.repositories.RealmStorageRepository
import com.alejandrorios.condorsports.domain.repositories.TeamsRepository
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
