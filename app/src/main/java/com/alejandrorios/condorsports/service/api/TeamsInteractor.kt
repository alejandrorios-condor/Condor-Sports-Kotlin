package com.alejandrorios.condorsports.service.api

import com.alejandrorios.condorsports.models.Team
import com.alejandrorios.condorsports.service.network.RetrofitProvider
import com.alejandrorios.condorsports.service.network.RetrofitProviderImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class TeamsInteractor {

    interface OnTeamFinishedListener {
        fun onSuccess(teamList: Team)
        fun onFailure(t: Throwable)
    }

    fun getTeamsList(
        codeLeague: String,
        listener: OnTeamFinishedListener
    ) {
        val service: RetrofitProvider = RetrofitProviderImpl.retrofit.create()
        val call: Call<Team> = service.getTeamByLeague(codeLeague)

        call.enqueue(object : Callback<Team> {
            override fun onResponse(call: Call<Team>, response: Response<Team>) {
                response.body()?.let { listener.onSuccess(it) }
            }

            override fun onFailure(call: Call<Team>, t: Throwable) {
                listener.onFailure(t)
            }

        })
    }
}