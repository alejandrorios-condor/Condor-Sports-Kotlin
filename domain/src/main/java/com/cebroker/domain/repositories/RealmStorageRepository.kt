package com.cebroker.domain.repositories

import io.realm.RealmObject

/**
 * Created by Alejandro Rios on 2019-07-25
 */
interface RealmStorageRepository {

    fun getTeamBadgeByName(teamName: String): String?

    fun <E : RealmObject> saveList(objects: Iterable<E>, clazz: Class<E>)
}
