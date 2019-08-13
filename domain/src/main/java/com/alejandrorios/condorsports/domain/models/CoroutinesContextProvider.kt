package com.alejandrorios.condorsports.domain.models

import kotlin.coroutines.CoroutineContext

/**
 * Created by Alejandro Rios on 2019-07-22
 */
data class CoroutinesContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)
