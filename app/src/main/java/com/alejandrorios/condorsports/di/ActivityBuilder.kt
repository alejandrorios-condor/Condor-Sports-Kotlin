package com.alejandrorios.condorsports.di

import com.alejandrorios.condorsports.ui.mainActivity.MainActivity
import com.alejandrorios.condorsports.ui.mainActivity.MainActivityModule
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivity
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Alejandro Rios on 2019-07-23
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(TeamDetailsActivityModule::class)])
    abstract fun bindTeamDetailsActivity(): TeamDetailsActivity
}
