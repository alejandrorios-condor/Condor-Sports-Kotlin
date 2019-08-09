package com.cebroker.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alejandro Rios on 2019-07-23
 */
data class APIEventsList(

    @SerializedName("events")
    var events: List<APIEvents>? = null
)