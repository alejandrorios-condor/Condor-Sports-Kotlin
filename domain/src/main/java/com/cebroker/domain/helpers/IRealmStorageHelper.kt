package com.cebroker.domain.helpers

import io.realm.RealmObject

/**
 * Created by Alejandro Rios on 2019-07-24
 */
interface IRealmStorageHelper {

    fun getTeamBadgeByName(teamName: String): String?

    fun <E : RealmObject> saveList(objects: Iterable<E>, clazz: Class<E>)
}
