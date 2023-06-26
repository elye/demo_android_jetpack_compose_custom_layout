package com.example.customlayout

import androidx.compose.ui.Modifier

fun Float.format(digits: Int) = "%.${digits}f".format(this)

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}
