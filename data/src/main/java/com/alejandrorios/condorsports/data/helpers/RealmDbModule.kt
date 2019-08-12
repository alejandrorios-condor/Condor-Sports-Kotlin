package com.alejandrorios.condorsports.data.helpers

import com.alejandrorios.condorsports.data.models.TeamData
import io.realm.annotations.RealmModule

@RealmModule(classes = [TeamData::class])
class RealmDbModule { }
