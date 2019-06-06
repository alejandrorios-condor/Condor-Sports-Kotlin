package com.cebroker.domain.repositories

import com.cebroker.domain.models.Teams

interface TeamsListRepository {

    suspend fun getTeams(codeLeague: String): List<Teams>
}