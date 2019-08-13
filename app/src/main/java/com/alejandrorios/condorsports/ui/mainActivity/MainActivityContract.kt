package com.alejandrorios.condorsports.ui.mainActivity

import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.ui.BaseCoroutinePresenter
import com.alejandrorios.condorsports.ui.BaseView
import com.alejandrorios.condorsports.domain.models.Teams

interface MainActivityContract {

    interface View : BaseView {
        fun setupTeamsList(teams: List<Teams>)

        fun showProgress()

        fun hideProgress()

        fun showMsg(show: Boolean)

        fun showTeamDetails(teamJson: String)
    }

    interface Presenter : BaseCoroutinePresenter<View>, TeamListAdapter.Delegate {
        fun getTeamsList(leagueCode: String)
    }
}
