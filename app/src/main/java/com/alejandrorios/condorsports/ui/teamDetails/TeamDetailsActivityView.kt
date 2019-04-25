package com.alejandrorios.condorsports.ui.teamDetails

import com.alejandrorios.condorsports.models.Events
import com.alejandrorios.condorsports.models.EventsData
import com.alejandrorios.condorsports.models.TeamData

interface TeamDetailsActivityView {
    interface Presenter {
        fun fetchEventsData(team: TeamData?)

        fun goWebsite(url: String)

        fun goFacebook(facebook: String)

        fun goTwitter(twitter: String)

        fun goInstagram(instagram: String)

        fun goYouTube(youtube: String)
    }

    interface GetEventsInteractor {

        interface OnFinishedListener {
            fun onFinished(eventsList: Events)

            fun onFailure(t: Throwable)
        }

        fun getEventsList(teamId: String, onFinishedListener: OnFinishedListener)
    }

    fun showProgress(show: Boolean)

    fun setupEventsList(eventsData: List<EventsData>)
}