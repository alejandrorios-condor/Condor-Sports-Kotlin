package com.alejandrorios.condorsports.domain.repositories

import com.alejandrorios.condorsports.domain.models.Events
import com.alejandrorios.condorsports.domain.models.Teams

/**
 * Created by Alejandro Rios on 2019-07-22
 */
interface TeamsRepository {

    suspend fun getTeamByLeague(codeLeagueId: String): List<Teams>

    suspend fun getEventsByTeam(teamId: String): List<Events>
}
