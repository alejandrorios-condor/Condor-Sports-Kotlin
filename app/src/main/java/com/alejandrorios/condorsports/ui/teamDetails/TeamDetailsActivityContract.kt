package com.alejandrorios.condorsports.ui.teamDetails

import com.alejandrorios.condorsports.ui.BaseCoroutinePresenter
import com.alejandrorios.condorsports.ui.BaseView
import com.alejandrorios.condorsports.domain.models.Events
import com.alejandrorios.condorsports.domain.models.Teams

interface TeamDetailsActivityContract {

    interface View : BaseView {
        fun showProgress()

        fun hideProgress()

        fun setupEventsList(eventsData: List<Events>)
    }

    interface Presenter : BaseCoroutinePresenter<View> {
        fun fetchEventsData(team: Teams)

        fun goWebsite(url: String)

        fun goFacebook(facebook: String)

        fun goTwitter(twitter: String)

        fun goInstagram(instagram: String)

        fun goYouTube(youtube: String)
    }
}
