package com.alejandrorios.condorsports.ui.mainActivity

import android.content.Context
import android.content.Intent
import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.common.RealmManager
import com.alejandrorios.condorsports.models.Team
import com.alejandrorios.condorsports.models.TeamData
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivity
import com.google.gson.Gson

class MainActivityPresenter(
    private val context: Context,
    private val mainActivity: MainActivityView,
    private val teamsData: MainActivityView.GetTeamsInteractor
) : MainActivityView.Presenter, MainActivityView.GetTeamsInteractor.OnFinishedListener, TeamListAdapter.Delegate {

    override fun getTeamsList(leagueCode: String) {
        mainActivity.showProgress(true)
        teamsData.getTeamsList(leagueCode, this)
    }

    override fun onFinished(teamList: Team) {
        mainActivity.showProgress(false)
        teamList.data?.let { RealmManager.instance.saveList(it, TeamData::class.java) }
        teamList.data?.let { mainActivity.setupTeamsList(it) }
    }

    override fun onFailure(t: Throwable) {
        mainActivity.showProgress(false)
        mainActivity.showMsg(true)
    }

    override fun onTeamClicked(team: TeamData?) {
        val intent = Intent(context, TeamDetailsActivity::class.java)
        val teamJson: String = Gson().toJson(team)

        intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("teamData", teamJson)
        }

        context.startActivity(intent)
    }
}