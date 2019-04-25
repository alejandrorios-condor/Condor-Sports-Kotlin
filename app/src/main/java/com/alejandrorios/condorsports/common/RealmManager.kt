package com.alejandrorios.condorsports.common

import com.alejandrorios.condorsports.models.TeamData
import io.realm.Realm
import io.realm.RealmObject

open class RealmManager {

    private var realm: Realm = Realm.getDefaultInstance()

    companion object {

        private var INSTANCE: RealmManager? = RealmManager()

        val instance: RealmManager
            get() {
                if (INSTANCE == null) {
                    INSTANCE = RealmManager()
                }
                return INSTANCE as RealmManager
            }
    }

    fun getTeamByName(teamName: String): String? {
        val results: TeamData? = realm.where(TeamData::class.java).equalTo("idTeam", teamName).findFirst()

        val url: String?

        url = try {
            results?.strTeamBadge
        } catch (e: NullPointerException) {
            e.printStackTrace()
            ""
        }

        return url
    }

    fun <E : RealmObject> saveList(objects: Iterable<E>, clazz: Class<E>) {
        realm.executeTransaction { realm.copyToRealmOrUpdate(objects) }
    }
}