package com.alejandrorios.condorsports.ui.mainActivity

import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.common.RealmManager
import com.alejandrorios.condorsports.models.Team
import com.alejandrorios.condorsports.models.TeamData
import com.alejandrorios.condorsports.service.api.TeamsInteractor
import com.google.gson.Gson

class MainActivityPresenter(
    private var mainActivity: MainActivityView?,
    private val teamsInteractor: TeamsInteractor
) : MainActivityView.Presenter, TeamsInteractor.OnTeamFinishedListener, TeamListAdapter.Delegate {

    override fun getTeamsList(leagueCode: String) {
        mainActivity?.showProgress(true)
        teamsInteractor.getTeamsList(leagueCode, this)
    }

    override fun onSuccess(teamList: Team) {
        mainActivity?.apply {
            showProgress(false)
            teamList.data?.let { RealmManager.instance.saveList(it, TeamData::class.java) }
            teamList.data?.let { setupTeamsList(it) }
        }
    }

    override fun onFailure(t: Throwable) {
        mainActivity?.apply {
            showProgress(false)
            showMsg(true)
        }
    }

    override fun onTeamClicked(team: TeamData?) {
        val teamJson: String = Gson().toJson(team)

        mainActivity?.showTeamDetails(teamJson)
    }

    fun onDestroy() {
        mainActivity = null
    }
}