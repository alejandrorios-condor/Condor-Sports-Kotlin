package com.alejandrorios.condorsports.ui.mainActivity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.adapters.TeamListAdapter
import com.alejandrorios.condorsports.utils.*
import com.alejandrorios.condorsports.ui.BaseActivity
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivity
import com.cebroker.domain.models.Teams
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityContract.View, SpeedDialView.OnActionSelectedListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.fabChangeLeague)
    lateinit var fab: SpeedDialView

    @BindView(R.id.pbTeamsList)
    lateinit var pbTeamsList: View

    @BindView(R.id.rvTeamsList)
    lateinit var rvTeamsList: RecyclerView

    @BindView(R.id.txtTeamsEmpty)
    lateinit var txtTeamsEmpty: TextView

    private var teamsList: List<Teams>? = null
    private var teamAdapter: TeamListAdapter? = null
    private var decoration = SpacesItemDecoration(SPACE_DECORATOR)
    private var shortAnimTime: Long? = ZERO

    @Inject
    lateinit var presenter: MainActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        lifecycle.addObserver(presenter)
        presenter.bind(this)
    }

    override fun onResume() {
        super.onResume()
        shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
        presenter.getTeamsList(getString(R.string.spanish_league_code))
    }

    override fun setupTeamsList(teams: List<Teams>) {
        val llm = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val resId: Int = R.anim.recycler_animation_falldown
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, resId)

        rvTeamsList.removeItemDecoration(decoration)
        teamsList = teams

        llm.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        teamAdapter = TeamListAdapter(teamsList!!)

        rvTeamsList.apply {
            layoutManager = llm
            layoutAnimation = animation
            adapter = teamAdapter
            addItemDecoration(decoration)
        }

        teamAdapter!!.delegate = presenter

        fab.apply {
            addActionItem(
                SpeedDialActionItem.Builder(R.id.fab_uefa_champions_league, R.drawable.ic_russian_league)
                    .setLabel(getString(R.string.russian_premier_league_title))
                    .setLabelBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, theme))
                    .create()
            )

            addActionItem(
                SpeedDialActionItem.Builder(R.id.fab_english_premier_league, R.drawable.ic_english)
                    .setLabel(getString(R.string.english_league_title))
                    .setLabelBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, theme))
                    .create()
            )

            addActionItem(
                SpeedDialActionItem.Builder(R.id.fab_spanish_league, R.drawable.ic_la_liga)
                    .setLabel(getString(R.string.spanish_league_title))
                    .setLabelBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, theme))
                    .create()
            )
        }

        fab.setOnActionSelectedListener(this)
    }

    override fun onActionSelected(actionItem: SpeedDialActionItem?): Boolean {
        when (actionItem?.id) {
            R.id.fab_spanish_league -> presenter!!.getTeamsList(getString(R.string.spanish_league_code))
            R.id.fab_english_premier_league -> presenter!!.getTeamsList(getString(R.string.english_league_code))
            R.id.fab_uefa_champions_league -> presenter!!.getTeamsList(getString(R.string.russian_premier_league_code))
        }
        clearPost()
        return false
    }

    override fun showProgress() {
        rvTeamsList.visibility = View.GONE
        rvTeamsList.animate().setDuration(shortAnimTime!!).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeamsList.visibility = View.GONE
                }
            })
        pbTeamsList.visibility = View.VISIBLE
        pbTeamsList.animate().setDuration(shortAnimTime!!).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeamsList.visibility = View.VISIBLE
                }
            })
    }

    override fun hideProgress() {
        rvTeamsList.visibility = View.VISIBLE
        rvTeamsList.animate().setDuration(shortAnimTime!!).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeamsList.visibility = View.VISIBLE
                }
            })
        pbTeamsList.visibility = View.GONE
        pbTeamsList.animate().setDuration(shortAnimTime!!).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeamsList.visibility = View.GONE
                }
            })
    }

    override fun showMsg(show: Boolean) {
        rvTeamsList.visibility = if (show) View.GONE else View.VISIBLE
        txtTeamsEmpty.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun clearPost() {
        teamsList = emptyList()
        rvTeamsList.removeItemDecoration(decoration)
        teamAdapter!!.notifyDataSetChanged()
    }

    override fun showTeamDetails(teamJson: String) {
        val intent = Intent(applicationContext, TeamDetailsActivity::class.java)

        intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra(TEAM_DATA, teamJson)
        }

        applicationContext.startActivity(intent)
    }
}
