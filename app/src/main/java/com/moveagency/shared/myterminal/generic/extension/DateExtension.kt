package com.moveagency.shared.myterminal.generic.extension

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

fun LocalDate.toDisplayString() = "$dayOfMonth-$monthNumber-$year"

fun LocalTime.toDisplayString() = "%02d:%02d".format(hour, minute)
