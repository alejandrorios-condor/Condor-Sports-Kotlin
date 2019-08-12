package com.alejandrorios.condorsports

import com.alejandrorios.condorsports.data.helpers.RealmDbModule
import com.alejandrorios.condorsports.di.AppComponent
import com.alejandrorios.condorsports.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration

class CondorSportsApplication : DaggerApplication() {

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("condorsports.realm")
            .modules(RealmDbModule())
            .build()
        Realm.setDefaultConfiguration(config)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent = component
        component.inject(this)
        return component
    }

}
