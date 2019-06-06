package com.cebroker.data.mapper

import com.cebroker.data.models.APITeams
import com.cebroker.domain.models.Teams
import java.util.function.Function

object APITeamsMapper : Function<APITeams, Teams> {

    override fun apply(t: APITeams): Teams {
        return Teams(
            t.idTeam,
            t.strTeam,
            t.strAlternate,
            t.intFormedYear,
            t.strStadium,
            t.strWebsite,
            t.strFacebook,
            t.strTwitter,
            t.strInstagram,
            t.strDescriptionEN,
            t.strTeamBadge,
            t.strTeamJersey,
            t.strYoutube
        )
    }
}
