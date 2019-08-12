package com.alejandrorios.condorsports.data.repository

import com.alejandrorios.condorsports.data.mapper.APIEventsMapper
import com.alejandrorios.condorsports.data.mapper.APITeamsMapper
import com.alejandrorios.condorsports.data.network.RetrofitClient
import com.alejandrorios.condorsports.data.network.api.TeamsService
import com.alejandrorios.condorsports.domain.models.Events
import com.alejandrorios.condorsports.domain.models.Teams
import com.alejandrorios.condorsports.domain.repositories.TeamsRepository

class TeamRepositoryImpl(
    private val retrofitClient: RetrofitClient,
    private val teamsService: TeamsService
) : TeamsRepository {

    override suspend fun getTeamByLeague(codeLeagueId: String): List<Teams> {
        return retrofitClient.apiCall(teamsService.getTeamByLeague(codeLeagueId))
            .body()?.teams?.map { apiTeams ->
            APITeamsMapper.map(apiTeams)
        } ?: throw Throwable()
    }

    override suspend fun getEventsByTeam(teamId: String): List<Events> {
        return retrofitClient.apiCall(teamsService.getEventsByTeam(teamId))
            .body()?.events?.map { apiEvents ->
            APIEventsMapper.map(apiEvents)
        } ?: throw Throwable()
    }
}
