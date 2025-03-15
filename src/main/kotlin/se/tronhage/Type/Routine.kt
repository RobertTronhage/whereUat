package se.tronhage.Type

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Routine(
        @Id val userId: String,
        var monday: String = "off",
        var tuesday: String = "off",
        var wednesday: String = "off",
        var thursday: String = "off",
        var friday: String = "off",
        var saturday: String = "off",
        var sunday: String = "off"
) {

    fun getRoutineForDay(day: String): String {
        return when (day.lowercase()) {
            "monday" -> monday
            "tuesday" -> tuesday
            "wednesday" -> wednesday
            "thursday" -> thursday
            "friday" -> friday
            else -> "Off"
        }
    }
}
