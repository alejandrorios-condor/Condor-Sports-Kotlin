package com.alejandrorios.condorsports.utils

import android.graphics.drawable.Drawable
import android.widget.TextView

fun TextView.setTopDrawable(drawable: Drawable) {
    this.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
}