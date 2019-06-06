package com.alejandrorios.condorsports.service.api

import com.alejandrorios.condorsports.service.network.RetrofitProvider
import com.alejandrorios.condorsports.service.network.RetrofitProviderImpl
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivityView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.create

class GetEventsList : TeamDetailsActivityView.GetEventsInteractor {

    override fun getEventsList(
        teamId: String,
        onFinishedListener: TeamDetailsActivityView.GetEventsInteractor.OnFinishedListener
    ) {
        val service: RetrofitProvider = RetrofitProviderImpl.retrofit.create()

//        CoroutineScope(Dispatchers.IO).launch {
//            val request = service.getEventsByTeam(teamId)
//            withContext(Dispatchers.IO) {
//                try {
//                    val response = request.await()
//
//                    if (response.isSuccessful) {
//                        response.body()?.let { onFinishedListener.onFinished(it) }
//                    } else {
//                        response.errorBody().let { onFinishedListener.onFailure(Throwable(it.toString())) }
//                    }
//                } catch (e: Throwable) {
//                    onFinishedListener.onFailure(Throwable(e))
//                    e.printStackTrace()
//                }
//            }
//        }
    }
}
