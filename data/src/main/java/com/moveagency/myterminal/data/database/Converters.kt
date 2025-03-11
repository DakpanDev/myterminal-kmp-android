package com.moveagency.myterminal.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.moveagency.myterminal.data.database.entity.CheckinRows
import com.moveagency.myterminal.data.database.entity.States
import org.koin.core.annotation.Factory
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Factory
@ProvidedTypeConverter
class Converters {

    @TypeConverter
    fun fromFlightStates(states: States): String {
        return states.values.joinToString(separator = CommaSeparator)
    }

    @TypeConverter
    fun toFlightStates(states: String): States {
        return states.split(CommaSeparator).toList().let {
            States(it)
        }
    }

    @TypeConverter
    fun fromCheckinRows(checkinRows: CheckinRows): String {
        return checkinRows.values.joinToString(separator = CommaSeparator)
    }

    @TypeConverter
    fun toCheckinRows(checkinRows: String): CheckinRows {
        return checkinRows.split(CommaSeparator).toList().let {
            CheckinRows(it)
        }
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String {
        return time.format(DateTimeFormatter.ofPattern(TimeFormat))
    }

    @TypeConverter
    fun toLocalTime(time: String): LocalTime {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TimeFormat))
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.format(DateTimeFormatter.ofPattern(DateTimeFormat))
    }

    @TypeConverter
    fun toLocalDateTime(dateTime: String): LocalDateTime {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DateTimeFormat))
    }

    companion object {

        private const val CommaSeparator = ","
        private const val DateFormat = "yyyy-MM-dd"
        private const val TimeFormat = "HH:mm"
        private const val DateTimeFormat = "${DateFormat}:$TimeFormat"
    }
}
