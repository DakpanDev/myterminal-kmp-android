package com.moveagency.myterminal.generic.extension

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.toDisplayString() = "$dayOfMonth-$monthValue-$year"

fun LocalTime.toDisplayString() = format(DateTimeFormatter.ofPattern("HH:mm"))
