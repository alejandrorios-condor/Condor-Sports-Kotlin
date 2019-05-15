package com.alejandrorios.condorsports.common

import android.graphics.drawable.Drawable
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun launchSilent(
    context: CoroutineContext = Dispatchers.Default,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    GlobalScope.launch(context, start, block)
}

fun TextView.setTopDrawable(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
}