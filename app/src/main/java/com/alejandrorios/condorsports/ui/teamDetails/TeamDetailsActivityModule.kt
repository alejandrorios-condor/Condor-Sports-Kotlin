package com.alejandrorios.condorsports.ui.teamDetails

import com.cebroker.domain.interactor.Interactor
import com.cebroker.domain.models.CoroutinesContextProvider
import com.cebroker.domain.models.Events
import dagger.Module
import dagger.Provides

/**
 * Created by Alejandro Rios on 2019-07-23
 */
@Module
class TeamDetailsActivityModule {

    @Provides
    fun provideTeamDetailsActivityPresenter(
        coroutinesContextProvider: CoroutinesContextProvider,
        getTeamsInteractor: Interactor<List<Events>, String>
    ): TeamDetailsActivityContract.Presenter {
        return TeamDetailsPresenter(
            coroutinesContextProvider,
            getTeamsInteractor
        )
    }
}
