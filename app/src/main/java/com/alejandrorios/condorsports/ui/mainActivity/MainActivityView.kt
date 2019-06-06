package com.alejandrorios.condorsports.ui.mainActivity

import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.models.Team
import com.alejandrorios.condorsports.models.TeamData

interface MainActivityView{
    interface Presenter : TeamListAdapter.Delegate {
        fun getTeamsList(leagueCode: String)
    }

    fun setupTeamsList(teams: List<TeamData>)

    fun showProgress(show: Boolean)

    fun showMsg(show: Boolean)

    fun showTeamDetails(teamJson: String)
}