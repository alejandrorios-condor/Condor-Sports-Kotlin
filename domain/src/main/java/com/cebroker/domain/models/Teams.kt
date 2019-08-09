package com.cebroker.domain.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Teams(
    @PrimaryKey var idTeam: String? = null,
    var strTeam: String? = null,
    var strAlternate: String? = null,
    var intFormedYear: String? = null,
    var strStadium: String? = null,
    var strWebsite: String? = null,
    var strFacebook: String? = null,
    var strTwitter: String? = null,
    var strInstagram: String? = null,
    var strDescriptionEN: String? = null,
    var strTeamBadge: String? = null,
    var strTeamJersey: String? = null,
    var strYoutube: String? = null
) : RealmObject() {

    fun copy(
        strTeam: String? = this.strTeam,
        strAlternate: String? = this.strAlternate,
        intFormedYear: String? = this.intFormedYear,
        strStadium: String? = this.strStadium,
        strWebsite: String? = this.strWebsite,
        strFacebook: String? = this.strFacebook,
        strTwitter: String? = this.strTwitter,
        strInstagram: String? = this.strInstagram,
        strDescriptionEN: String? = this.strDescriptionEN,
        strTeamBadge: String? = this.strTeamBadge,
        strTeamJersey: String? = this.strTeamJersey,
        strYoutube: String? = this.strYoutube
    ) = Teams(
        strTeam,
        strAlternate,
        intFormedYear,
        strStadium,
        strWebsite,
        strFacebook,
        strTwitter,
        strInstagram,
        strDescriptionEN,
        strTeamBadge,
        strTeamJersey,
        strYoutube
    )
}