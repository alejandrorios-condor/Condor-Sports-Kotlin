package com.cebroker.data;

import com.cebroker.data.models.APITeamsList

interface TeamsRepository {

    suspend fun getTeamsList(codeLeague: String): APITeamsList
}
