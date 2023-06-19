package com.example.customlayout

fun Float.format(digits: Int) = "%.${digits}f".format(this)