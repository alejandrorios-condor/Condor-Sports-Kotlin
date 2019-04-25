package com.alejandrorios.condorsports.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Events{

    @SerializedName("events")
    @Expose
    var events: List<EventsData>? = null
}