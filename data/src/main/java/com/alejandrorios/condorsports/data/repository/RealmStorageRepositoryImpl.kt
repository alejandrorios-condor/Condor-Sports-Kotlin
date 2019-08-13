package com.alejandrorios.condorsports.data.repository

import com.alejandrorios.condorsports.domain.helpers.IRealmStorageHelper
import com.alejandrorios.condorsports.domain.repositories.RealmStorageRepository
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
