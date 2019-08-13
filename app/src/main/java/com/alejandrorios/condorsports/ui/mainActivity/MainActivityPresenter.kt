package com.alejandrorios.condorsports.ui.mainActivity

import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.domain.interactor.Interactor
import com.alejandrorios.condorsports.domain.models.CoroutinesContextProvider
import com.alejandrorios.condorsports.domain.models.Teams
import com.google.gson.Gson
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class MainActivityPresenter(
    override val coroutinesContextProvider: CoroutinesContextProvider,
    private val getTeamsInteractor: Interactor<List<Teams>, String>
//    private val saveTeamsInteractor: Interactor<Unit, List<Teams>>
) : MainActivityContract.Presenter, TeamListAdapter.Delegate {

    override val parentJob = Job()
    override var view: MainActivityContract.View? = null
    private var teamsList: List<Teams>? = null

    override fun getTeamsList(leagueCode: String) {
        view?.showProgress()

        launchJobOnMainDispatchers {
            try {
                teamsList = withContext(coroutinesContextProvider.backgroundContext) {
                    getTeamsInteractor(leagueCode)
                }

                teamsList?.let {
//                    saveTeamsInteractor.invoke(it)
//                    RealmManager.instance.saveList(it, TeamData::class.java)
                    view?.hideProgress()
                    view?.setupTeamsList(it)
                }
            } catch (t: Throwable) {
                view?.hideProgress()
                t.printStackTrace()
            }
        }
    }

    override fun onTeamClicked(team: Teams?) {
        val teamJson: String = Gson().toJson(team)

        view?.showTeamDetails(teamJson)
    }
}
