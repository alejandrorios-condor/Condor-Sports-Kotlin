package com.alejandrorios.condorsports.service.network

import com.alejandrorios.condorsports.models.Events
import com.alejandrorios.condorsports.models.Team
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitProvider {

    @GET("lookup_all_teams.php")
    fun getTeamByLeague(@Query("id") userId: String): Call<Team>

    @GET("eventsnext.php")
    fun getEventsByTeam(@Query("id") teamId: String): Call<Events>
}