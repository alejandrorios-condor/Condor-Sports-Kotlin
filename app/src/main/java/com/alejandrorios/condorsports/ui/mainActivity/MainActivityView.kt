package com.alejandrorios.condorsports.ui.mainActivity

import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.models.Team
import com.alejandrorios.condorsports.models.TeamData

interface MainActivityView{
    interface Presenter : TeamListAdapter.Delegate {
        fun getTeamsList(leagueCode: String)
    }

    interface GetTeamsInteractor {

        interface OnFinishedListener {
            fun onFinished(teamList: Team)

            fun onFailure(t: Throwable)
        }

        fun getTeamsList(codeLeague: String, onFinishedListener: OnFinishedListener)
    }

    fun setupTeamsList(teams: List<TeamData>)

    fun showProgress(show: Boolean)

    fun showMsg(show: Boolean)
}
