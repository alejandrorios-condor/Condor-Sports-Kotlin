package com.alejandrorios.condorsports.ui.mainActivity

import com.alejandrorios.condorsports.data.helpers.RealmStorageHelper
import com.alejandrorios.condorsports.domain.interactor.Interactor
import com.alejandrorios.condorsports.domain.interactor.SaveTeamsInteractor
import com.alejandrorios.condorsports.domain.models.CoroutinesContextProvider
import com.alejandrorios.condorsports.domain.models.Teams
import dagger.Module
import dagger.Provides

/**
 * Created by Alejandro Rios on 2019-07-22
 */
@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityPresenter(
        coroutinesContextProvider: CoroutinesContextProvider,
        getTeamsInteractor: Interactor<List<Teams>, String>
//        saveTeamsInteractor: SaveTeamsInteractor
    ): MainActivityContract.Presenter {
        return MainActivityPresenter(
            coroutinesContextProvider,
            getTeamsInteractor
//            saveTeamsInteractor
        )
    }
}
