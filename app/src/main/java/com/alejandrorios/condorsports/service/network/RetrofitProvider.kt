package com.alejandrorios.condorsports.service.network

import com.alejandrorios.condorsports.models.Events
import com.alejandrorios.condorsports.models.Team
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitProvider {

    @GET("lookup_all_teams.php")
    fun getTeamByLeague(@Query("id") userId: String): Deferred<Response<Team>>

    @GET("eventsnext.php")
    fun getEventsByTeam(@Query("id") teamId: String): Deferred<Events>
}