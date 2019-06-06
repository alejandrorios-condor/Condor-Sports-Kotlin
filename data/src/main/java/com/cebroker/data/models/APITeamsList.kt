package com.cebroker.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class APITeamsList {

    @SerializedName("teams")
    @Expose
    var data: List<APITeams>? = null
}