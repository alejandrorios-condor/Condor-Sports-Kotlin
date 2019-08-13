package com.alejandrorios.condorsports.data.network.api

import com.alejandrorios.condorsports.data.models.APIEventsList
import com.alejandrorios.condorsports.data.models.APITeamsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamsService {

    @GET("lookup_all_teams.php")
    suspend fun getTeamByLeague(@Query("id") userId: String): Response<APITeamsList>

    @GET("eventsnext.php")
    suspend fun getEventsByTeam(@Query("id") teamId: String): Response<APIEventsList>
}
