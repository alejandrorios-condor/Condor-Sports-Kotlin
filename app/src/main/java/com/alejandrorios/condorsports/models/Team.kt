package com.alejandrorios.condorsports.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Team {

    @SerializedName("teams")
    @Expose
    var data: List<TeamData>? = null
}