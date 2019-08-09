package com.cebroker.data.mapper

import com.cebroker.data.models.APIEvents
import com.cebroker.domain.models.Events

/**
 * Created by Alejandro Rios on 2019-07-23
 */
object APIEventsMapper {

    fun map(t: APIEvents) = Events(
        t.idHomeTeam,
        t.idAwayTeam,
        t.strHomeTeam,
        t.strAwayTeam,
        t.dateEvent
    )

    fun map(t: Events) = APIEvents(
        t.idHomeTeam,
        t.idAwayTeam,
        t.strHomeTeam,
        t.strAwayTeam,
        t.dateEvent
    )
}
