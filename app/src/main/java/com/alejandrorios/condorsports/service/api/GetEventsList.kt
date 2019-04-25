package com.alejandrorios.condorsports.service.api

import com.alejandrorios.condorsports.models.Events
import com.alejandrorios.condorsports.service.network.RetrofitProvider
import com.alejandrorios.condorsports.service.network.RetrofitProviderImpl
import com.alejandrorios.condorsports.ui.teamDetails.TeamDetailsActivityView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class GetEventsList : TeamDetailsActivityView.GetEventsInteractor {

    override fun getEventsList(
        teamId: String,
        onFinishedListener: TeamDetailsActivityView.GetEventsInteractor.OnFinishedListener
    ) {
        val service: RetrofitProvider = RetrofitProviderImpl.retrofit.create()
        val call: Call<Events> = service.getEventsByTeam(teamId)

        call.enqueue(object : Callback<Events> {
            override fun onResponse(call: Call<Events>, response: Response<Events>) {
                response.body()?.let { onFinishedListener.onFinished(it) }
            }

            override fun onFailure(call: Call<Events>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }

        })
    }
}