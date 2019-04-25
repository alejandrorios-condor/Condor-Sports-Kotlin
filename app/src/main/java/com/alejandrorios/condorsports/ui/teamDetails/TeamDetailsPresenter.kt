package com.alejandrorios.condorsports.ui.teamDetails

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.alejandrorios.condorsports.models.Events
import com.alejandrorios.condorsports.models.TeamData

class TeamDetailsPresenter(
    private val context: Context,
    private val teamActivity: TeamDetailsActivityView,
    private val eventsData: TeamDetailsActivityView.GetEventsInteractor
) : TeamDetailsActivityView.Presenter, TeamDetailsActivityView.GetEventsInteractor.OnFinishedListener {

    override fun fetchEventsData(team: TeamData?) {
        teamActivity.showProgress(true)
        team?.idTeam?.let { eventsData.getEventsList(it, this) }
    }

    override fun goWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        context.startActivity(intent)
    }

    override fun goFacebook(facebook: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$facebook"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        context.startActivity(intent)
    }

    override fun goTwitter(twitter: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$twitter"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        context.startActivity(intent)
    }

    override fun goInstagram(instagram: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$instagram"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        context.startActivity(intent)
    }

    override fun goYouTube(youtube: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$youtube"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        context.startActivity(intent)
    }

    override fun onFinished(eventsList: Events) {
        teamActivity.apply {
            eventsList.events?.let { setupEventsList(it) }
        }
    }

    override fun onFailure(t: Throwable) {
        teamActivity.apply {
            showProgress(false)
        }
    }
}