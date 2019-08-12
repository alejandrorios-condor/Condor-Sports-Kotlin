package com.alejandrorios.condorsports.domain.interactor

import com.alejandrorios.condorsports.domain.models.Teams
import com.alejandrorios.condorsports.domain.repositories.TeamsRepository

class GetTeamsInteractor(
    private val teamsRepository: TeamsRepository
) : Interactor<List<Teams>, String> {

    private var lastResponse: List<Teams>? = null

    override suspend fun invoke(params: String): List<Teams> {
        return try {
            teamsRepository.getTeamByLeague(params).apply {
                lastResponse = this
            }
        } catch (t: Throwable) {
            lastResponse?.let { it } ?: throw t
        }
    }
}
