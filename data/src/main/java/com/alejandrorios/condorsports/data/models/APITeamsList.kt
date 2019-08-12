package com.alejandrorios.condorsports.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alejandro Rios on 2019-07-23
 */
data class APITeamsList(
    @SerializedName("teams")
    var teams: List<APITeams>? = null
)
