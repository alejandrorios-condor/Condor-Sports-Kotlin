package com.cebroker.data.repository

import com.cebroker.data.TeamsRepository
import com.cebroker.data.models.APITeamsList
import com.cebroker.data.network.api.TeamsService

class TeamRepositoryImpl(private val teamsService: TeamsService) : TeamsRepository {

    override suspend fun getTeamsList(codeLeague: String): APITeamsList {
        return teamsService.getTeamByLeague(codeLeague)
    }
}