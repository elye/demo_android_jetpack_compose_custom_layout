package com.example.customlayout

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

fun Float.format(digits: Int) = "%.${digits}f".format(this)

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

val GrayAlpha: Color
    get() = Color(148, 148, 148, 128)
