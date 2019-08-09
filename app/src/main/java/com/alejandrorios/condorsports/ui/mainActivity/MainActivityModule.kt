package com.alejandrorios.condorsports.ui.mainActivity

import com.cebroker.data.helpers.RealmStorageHelper
import com.cebroker.domain.interactor.Interactor
import com.cebroker.domain.interactor.SaveTeamsInteractor
import com.cebroker.domain.models.CoroutinesContextProvider
import com.cebroker.domain.models.Teams
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
