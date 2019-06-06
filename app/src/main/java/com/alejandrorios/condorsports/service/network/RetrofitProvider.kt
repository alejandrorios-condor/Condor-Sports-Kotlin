package com.alejandrorios.condorsports.service.network

import com.alejandrorios.condorsports.models.Events
import com.alejandrorios.condorsports.models.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitProvider {

    @GET("lookup_all_teams.php")
    suspend fun getTeamByLeague(@Query("id") userId: String): Response<Team>

    @GET("eventsnext.php")
    suspend fun getEventsByTeam(@Query("id") teamId: String): Events
}
