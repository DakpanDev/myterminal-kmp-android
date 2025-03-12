package com.moveagency.myterminal.generic.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

fun LocalDate.toDisplayString() = "$dayOfMonth-$monthNumber-$year"

fun LocalTime.toDisplayString() = "$hour:$minute"
