package com.alejandrorios.condorsports.common

import android.graphics.drawable.Drawable
import android.widget.TextView

fun TextView.setTopDrawable(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
}