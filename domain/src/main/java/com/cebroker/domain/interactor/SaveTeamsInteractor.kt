package com.cebroker.domain.interactor

import com.cebroker.domain.models.Teams
import com.cebroker.domain.repositories.RealmStorageRepository

/**
 * Created by Alejandro Rios on 2019-07-25
 */
class SaveTeamsInteractor(
    private val realmStorageRepository: RealmStorageRepository
) : Interactor<Unit, List<Teams>> {

    override suspend fun invoke(params: List<Teams>) {
        return realmStorageRepository.saveList(params, Teams::class.java)
    }
}
