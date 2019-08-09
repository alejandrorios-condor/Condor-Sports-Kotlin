package com.cebroker.domain.repositories

import com.cebroker.domain.models.Events
import com.cebroker.domain.models.Teams

/**
 * Created by Alejandro Rios on 2019-07-22
 */
interface TeamsRepository {

    suspend fun getTeamByLeague(codeLeagueId: String): List<Teams>

    suspend fun getEventsByTeam(teamId: String): List<Events>
}