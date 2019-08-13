package com.alejandrorios.condorsports.domain.interactor

import com.alejandrorios.condorsports.domain.models.Events
import com.alejandrorios.condorsports.domain.repositories.TeamsRepository

/**
 * Created by Alejandro Rios on 2019-07-23
 */
class GetEventsInteractor(
    private val teamsRepository: TeamsRepository
) : Interactor<List<Events>, String> {

    private var lastResponse: List<Events>? = null

    override suspend fun invoke(params: String): List<Events> {
        return try {
            teamsRepository.getEventsByTeam(params).apply {
                lastResponse = this
            }
        } catch (t: Throwable) {
            lastResponse?.let { it } ?: throw t
        }
    }
}
