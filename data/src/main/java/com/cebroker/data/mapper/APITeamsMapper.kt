package com.cebroker.data.mapper

import com.cebroker.data.models.APITeams
import com.cebroker.domain.models.Teams

object APITeamsMapper {

    fun map(t: APITeams) = Teams(
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

    fun map(t: Teams) = APITeams(
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
