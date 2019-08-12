package com.alejandrorios.condorsports.data.helpers

import android.content.Context
import com.alejandrorios.condorsports.data.models.TeamData
import com.alejandrorios.condorsports.domain.helpers.IRealmStorageHelper
import com.alejandrorios.condorsports.domain.models.Teams
import io.realm.Realm
import io.realm.RealmObject

/**
 * Created by Alejandro Rios on 2019-07-24
 */
class RealmStorageHelper(context: Context): IRealmStorageHelper {

    private var realm: Realm = Realm.getDefaultInstance()

//    companion object {
//
//        private var INSTANCE: RealmStorageHelper? = RealmStorageHelper()
//
//        val instance: RealmStorageHelper
//            get() {
//                if (INSTANCE == null) {
//                    INSTANCE = RealmStorageHelper()
//                }
//                return INSTANCE as RealmStorageHelper
//            }
//    }

    override fun getTeamBadgeByName(teamName: String): String? {
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

    override fun <E : RealmObject> saveList(objects: Iterable<E>, clazz: Class<E>) {
        realm.executeTransaction { realm.copyToRealmOrUpdate(objects) }
    }
}
