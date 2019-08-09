package com.alejandrorios.condorsports.di

import android.content.Context
import com.alejandrorios.condorsports.CondorSportsApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Alejandro Rios on 2019-06-12
 */
@Singleton
@Component(
    modules = [
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (ActivityBuilder::class),
        (InteractorsModule::class)]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Context): Builder
    }

    fun inject(condorSportsApplication: CondorSportsApplication)
}
