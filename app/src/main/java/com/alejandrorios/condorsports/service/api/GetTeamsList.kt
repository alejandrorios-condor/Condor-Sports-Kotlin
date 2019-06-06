package com.alejandrorios.condorsports.service.api

import com.alejandrorios.condorsports.service.network.RetrofitProvider
import com.alejandrorios.condorsports.service.network.RetrofitProviderImpl
import com.alejandrorios.condorsports.ui.mainActivity.MainActivityView
import kotlinx.coroutines.*
import retrofit2.create

class GetTeamsList : MainActivityView.GetTeamsInteractor {

    override fun getTeamsList(
        codeLeague: String,
        onFinishedListener: MainActivityView.GetTeamsInteractor.OnFinishedListener
    ) {
        return getInfoForRepo(codeLeague, onFinishedListener)
    }

    private fun getInfoForRepo(codeLeague: String,
                                       onFinishedListener: MainActivityView.GetTeamsInteractor.OnFinishedListener){
        val service: RetrofitProvider = RetrofitProviderImpl.retrofit.create()

        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getTeamByLeague(codeLeague)
            withContext(Dispatchers.IO) {
                try {

                    if (response.isSuccessful) {
                        response.body()?.let { onFinishedListener.onFinished(it) }
                    } else {
                        response.errorBody().let { onFinishedListener.onFailure(Throwable(it.toString())) }
                    }
                } catch (e: Throwable) {
                    onFinishedListener.onFailure(Throwable(e))
                    e.printStackTrace()
                }
            }
        }
    }
}
