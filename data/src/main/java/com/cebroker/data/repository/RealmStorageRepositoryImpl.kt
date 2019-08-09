package com.cebroker.data.repository

import com.cebroker.domain.helpers.IRealmStorageHelper
import com.cebroker.domain.repositories.RealmStorageRepository
import io.realm.RealmObject

/**
 * Created by Alejandro Rios on 2019-07-25
 */
class RealmStorageRepositoryImpl(private val realmStorageHelper: IRealmStorageHelper) : RealmStorageRepository {

    override fun getTeamBadgeByName(teamName: String): String? {
        return realmStorageHelper.getTeamBadgeByName(teamName)
    }

    override fun <E : RealmObject> saveList(objects: Iterable<E>, clazz: Class<E>) {
        realmStorageHelper.saveList(objects, clazz)
    }
}
