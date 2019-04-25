package com.alejandrorios.condorsports.service.api

import com.alejandrorios.condorsports.models.Team
import com.alejandrorios.condorsports.service.network.RetrofitProvider
import com.alejandrorios.condorsports.service.network.RetrofitProviderImpl
import com.alejandrorios.condorsports.ui.mainActivity.MainActivityView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class GetTeamsList : MainActivityView.GetTeamsInteractor {

    override fun getTeamsList(
        codeLeague: String,
        onFinishedListener: MainActivityView.GetTeamsInteractor.OnFinishedListener
    ) {
        val service: RetrofitProvider = RetrofitProviderImpl.retrofit.create()
        val call: Call<Team> = service.getTeamByLeague(codeLeague)

        call.enqueue(object : Callback<Team> {
            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                response.body()?.let { onFinishedListener.onFinished(it) }
            }

            override fun onFailure(call: Call<Team>, t: Throwable) {
                onFinishedListener.onFailure(t)
            }

        })
    }
}