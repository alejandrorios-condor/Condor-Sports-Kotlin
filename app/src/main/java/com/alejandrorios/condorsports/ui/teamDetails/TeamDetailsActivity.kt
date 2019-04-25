package com.alejandrorios.condorsports.ui.teamDetails

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.alejandrorios.condorsports.R
import com.alejandrorios.condorsports.adapters.EventsListAdapter
import com.alejandrorios.condorsports.common.ConfirmationDialog
import com.alejandrorios.condorsports.common.SpacesItemDecoration
import com.alejandrorios.condorsports.models.EventsData
import com.alejandrorios.condorsports.models.TeamData
import com.alejandrorios.condorsports.service.api.GetEventsList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.Gson

class TeamDetailsActivity : AppCompatActivity(), TeamDetailsActivityView,
    BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.collapsing_toolbar)
    lateinit var collapsing_toolbar: CollapsingToolbarLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.bottomNavTeam)
    lateinit var bottomNavTeam: BottomNavigationView

    @BindView(R.id.pbEventsList)
    lateinit var pbEventsList: View

    @BindView(R.id.imgTeam)
    lateinit var imgTeam: ImageView

    @BindView(R.id.imgTeamJersey)
    lateinit var imgTeamJersey: ImageView

    @BindView(R.id.txtTeamFormedYear)
    lateinit var txtTeamFormedYear: TextView

    @BindView(R.id.txtTeamDesc)
    lateinit var txtTeamDesc: TextView

    @BindView(R.id.rvEventsList)
    lateinit var rvEventsList: RecyclerView

    var presenter: TeamDetailsPresenter? = null
    var teamDataInfo: TeamData? = null
    private var dialog: ConfirmationDialog = ConfirmationDialog(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        try {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        intent.extras.run {
            teamDataInfo = Gson().run { fromJson(intent.getStringExtra("teamData"), TeamData::class.java) }
            teamDataInfo.let { collapsing_toolbar.title = it?.strAlternate }
        }

        presenter = TeamDetailsPresenter(applicationContext, this, GetEventsList())
        presenter!!.fetchEventsData(teamDataInfo)
    }

    override fun setupEventsList(eventsData: List<EventsData>) {
        val resId: Int = R.anim.recycler_animation_falldown
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, resId)

        txtTeamFormedYear.text = teamDataInfo?.intFormedYear
        txtTeamDesc.text = teamDataInfo?.strDescriptionEN

        Glide.with(this)
            .load(teamDataInfo?.strTeamBadge)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.ic_soccer_ball)
            .error(R.drawable.ic_no_img)
            .into(imgTeam)

        Glide.with(this)
            .load(teamDataInfo?.strTeamJersey)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.ic_soccer_ball)
            .error(R.drawable.ic_no_img)
            .into(imgTeamJersey)

        rvEventsList.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            layoutAnimation = animation
            adapter = EventsListAdapter(eventsData)
            addItemDecoration(SpacesItemDecoration(16))
        }

        bottomNavTeam.itemIconTintList = null
        bottomNavTeam.setOnNavigationItemSelectedListener(this)
        showProgress(false)
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.navWebSite -> {

                if (!teamDataInfo?.strWebsite.isNullOrEmpty()) {
                    teamDataInfo?.strWebsite?.let { presenter?.goWebsite(it) }
                } else {
                    showDialogMsg(R.string.team_details_no_web)
                }
            }
            R.id.navFacebook -> {
                if (!teamDataInfo?.strFacebook.isNullOrEmpty()) {
                    teamDataInfo?.strFacebook?.let { presenter?.goFacebook(it) }
                } else {
                    showDialogMsg(R.string.team_details_no_facebook)
                }
            }
            R.id.navTwitter -> {
                if (!teamDataInfo?.strTwitter.isNullOrEmpty()) {
                    teamDataInfo?.strTwitter?.let { presenter?.goTwitter(it) }
                } else {
                    showDialogMsg(R.string.team_details_no_twitter)
                }
            }
            R.id.navInstagram -> {
                if (!teamDataInfo?.strInstagram.isNullOrEmpty()) {
                    teamDataInfo?.strInstagram?.let { presenter?.goInstagram(it) }
                } else {
                    showDialogMsg(R.string.team_details_no_instagram)
                }
            }
            R.id.navYouTube -> {
                if (!teamDataInfo?.strYoutube.isNullOrEmpty()) {
                    teamDataInfo?.strYoutube?.let { presenter?.goYouTube(it) }
                } else {
                    showDialogMsg(R.string.team_details_no_youtube)
                }
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showProgress(show: Boolean) {
        val shortAnimTime: Int = resources.getInteger(android.R.integer.config_shortAnimTime)

        pbEventsList.visibility = if (show) View.VISIBLE else View.GONE
        pbEventsList.animate().setDuration(shortAnimTime.toLong()).alpha((if (show) 1 else 0).toFloat())
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbEventsList.visibility = if (show) View.VISIBLE else View.GONE
                }
            })
    }

    private fun showDialogMsg(msg: Int) {
        dialog.apply {
            showSimple(msg, true)
        }
    }
}