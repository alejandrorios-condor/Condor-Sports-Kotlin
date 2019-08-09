package com.alejandrorios.condorsports.ui.teamDetails

import android.content.Intent
import android.net.Uri
import com.cebroker.domain.interactor.Interactor
import com.cebroker.domain.models.CoroutinesContextProvider
import com.cebroker.domain.models.Events
import com.cebroker.domain.models.Teams
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class TeamDetailsPresenter(
    override val coroutinesContextProvider: CoroutinesContextProvider,
    private val getTeamsInteractor: Interactor<List<Events>, String>
) : TeamDetailsActivityContract.Presenter {

    override val parentJob = Job()
    override var view: TeamDetailsActivityContract.View? = null
    private var evensList: List<Events>? = null

    override fun fetchEventsData(team: Teams) {
        view?.showProgress()

        launchJobOnMainDispatchers {
            try {
                evensList = withContext(coroutinesContextProvider.backgroundContext) {
                    team.idTeam?.let { getTeamsInteractor(it) }
                }

                evensList?.let {
                    view?.hideProgress()
                    view?.setupEventsList(it)
                }
            } catch (t: Throwable) {
                view?.hideProgress()
                t.printStackTrace()
            }
        }
    }

    override fun goWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
//        context.startActivity(intent)
    }

    override fun goFacebook(facebook: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$facebook"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
//        context.startActivity(intent)
    }

    override fun goTwitter(twitter: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$twitter"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
//        context.startActivity(intent)
    }

    override fun goInstagram(instagram: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$instagram"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
//        context.startActivity(intent)
    }

    override fun goYouTube(youtube: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$youtube"))

        intent.apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
//        context.startActivity(intent)
    }
}
