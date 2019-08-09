package com.cebroker.data.helpers

import com.cebroker.data.models.TeamData
import io.realm.annotations.RealmModule

@RealmModule(classes = [TeamData::class])
class RealmDbModule { }
