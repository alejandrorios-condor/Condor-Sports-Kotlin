package com.cebroker.data.repository

import com.cebroker.data.mapper.APIEventsMapper
import com.cebroker.data.mapper.APITeamsMapper
import com.cebroker.data.network.RetrofitClient
import com.cebroker.data.network.api.TeamsService
import com.cebroker.domain.models.Events
import com.cebroker.domain.models.Teams
import com.cebroker.domain.repositories.TeamsRepository

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
