package com.alejandrorios.condorsports.ui.teamDetails

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CollapsingToolbarLayout
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
import com.alejandrorios.condorsports.ui.BaseActivity
import com.alejandrorios.condorsports.utils.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cebroker.domain.models.Events
import com.cebroker.domain.models.Teams
import com.google.gson.Gson
import javax.inject.Inject

class TeamDetailsActivity : BaseActivity(), TeamDetailsActivityContract.View,
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

    var teamDataInfo: Teams? = null
    private var dialog: ConfirmationDialog = ConfirmationDialog(this)
    private var shortAnimTime: Long? = ZERO

    @Inject
    lateinit var presenter: TeamDetailsActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        lifecycle.addObserver(presenter)
        presenter.bind(this)

        try {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        intent.extras.run {
            teamDataInfo = Gson().run { fromJson(intent.getStringExtra("teamData"), Teams::class.java) }
            teamDataInfo.let {
                collapsing_toolbar.title = it?.strAlternate
                presenter.fetchEventsData(it!!)
            }
        }

        shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
    }

    override fun setupEventsList(eventsData: List<Events>) {
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
            addItemDecoration(SpacesItemDecoration(SPACE_DECORATOR))
        }

        bottomNavTeam.itemIconTintList = null
        bottomNavTeam.setOnNavigationItemSelectedListener(this)
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

    override fun showProgress() {
        pbEventsList.visibility = View.VISIBLE
        pbEventsList.animate().setDuration(shortAnimTime!!.toLong()).alpha(ONE_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbEventsList.visibility = View.VISIBLE
                }
            })
    }

    override fun hideProgress() {
        pbEventsList.visibility = View.GONE
        pbEventsList.animate().setDuration(shortAnimTime!!.toLong()).alpha(ZERO_ALPHA)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    pbEventsList.visibility = View.GONE
                }
            })
    }

    private fun showDialogMsg(msg: Int) {
        dialog.apply {
            showSimple(msg, true)
        }
    }
}