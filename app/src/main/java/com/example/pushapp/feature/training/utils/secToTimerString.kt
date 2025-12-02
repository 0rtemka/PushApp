package com.example.pushapp.feature.training.utils

fun secToTimerString(sec: Int): String {
    val min = sec / 60
    val sec = sec % 60
    return "${min.toString().padStart(2, '0')}:${sec.toString().padStart(2, '0')}"
}