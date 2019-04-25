package com.alejandrorios.condorsports

import android.app.Application
import com.alejandrorios.condorsports.common.RealmDbModule
import io.realm.Realm
import io.realm.RealmConfiguration

class CondorSportsApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("condorsports.realm")
            .modules(RealmDbModule())
            .build()
        Realm.setDefaultConfiguration(config)
    }
}