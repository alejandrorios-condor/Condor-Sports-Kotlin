package com.alejandrorios.condorsports.domain.interactor

import com.alejandrorios.condorsports.domain.TeamBadgeRealmError
import com.alejandrorios.condorsports.domain.repositories.RealmStorageRepository

/**
 * Created by Alejandro Rios on 2019-07-25
 */
class GetTeamBadgeInteractor(
    private val realmStorageRepository: RealmStorageRepository
) : Interactor<String, String> {

    override suspend fun invoke(params: String): String {
        return realmStorageRepository.getTeamBadgeByName(params) ?: throw TeamBadgeRealmError()
    }
}
