package com.alejandrorios.condorsports.ui.mainActivity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
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
import com.alejandrorios.condorsports.common.SpacesItemDecoration
import com.alejandrorios.condorsports.models.TeamData
import com.alejandrorios.condorsports.service.api.TeamsInteractor
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivity
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView

class MainActivity : AppCompatActivity(), MainActivityView, SpeedDialView.OnActionSelectedListener {

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

    private var teamsList: List<TeamData>? = null
    private var presenter = MainActivityPresenter(this, TeamsInteractor())
    private var teamAdapter: TeamListAdapter? = null
    private var decoration = SpacesItemDecoration(16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        presenter!!.getTeamsList(getString(R.string.spanish_league_code))
    }

    override fun setupTeamsList(teams: List<TeamData>) {
        teamsList = teams
        val llm = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val resId: Int = R.anim.recycler_animation_falldown
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, resId)

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

    override fun showProgress(show: Boolean) {
        val shortAnimTime: Int = resources.getInteger(android.R.integer.config_shortAnimTime)

        rvTeamsList.visibility = if (show) View.GONE else View.VISIBLE
        rvTeamsList.animate().setDuration(shortAnimTime.toLong()).alpha((if (show) 0 else 1).toFloat())
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    rvTeamsList.visibility = if (show) View.GONE else View.VISIBLE
                }
            })
        pbTeamsList.visibility = if (show) View.VISIBLE else View.GONE
        pbTeamsList.animate().setDuration(shortAnimTime.toLong()).alpha((if (show) 1 else 0).toFloat())
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbTeamsList.visibility = if (show) View.VISIBLE else View.GONE
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
            putExtra("teamData", teamJson)
        }

        applicationContext.startActivity(intent)
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        super.onDestroy()
    }
}
