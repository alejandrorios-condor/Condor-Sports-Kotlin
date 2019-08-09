package com.cebroker.domain.interactor

import com.cebroker.domain.TeamBadgeRealmError
import com.cebroker.domain.repositories.RealmStorageRepository

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