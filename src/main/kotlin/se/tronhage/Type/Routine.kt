package se.tronhage.Type

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import se.tronhage.enum.Statuses

@Entity
data class Routine(
    @Id val userId: Long,
    @Enumerated(EnumType.STRING) var monday: Statuses = Statuses.OFF_OTHER,
    @Enumerated(EnumType.STRING) var tuesday: Statuses = Statuses.OFF_OTHER,
    @Enumerated(EnumType.STRING) var wednesday: Statuses = Statuses.OFF_OTHER,
    @Enumerated(EnumType.STRING) var thursday: Statuses = Statuses.OFF_OTHER,
    @Enumerated(EnumType.STRING) var friday: Statuses = Statuses.OFF_OTHER,
    @Enumerated(EnumType.STRING) var saturday: Statuses = Statuses.OFF_OTHER,
    @Enumerated(EnumType.STRING) var sunday: Statuses = Statuses.OFF_OTHER
) {
    fun getRoutineForDay(day: String): Statuses {
        return when (day.lowercase()) {
            "monday" -> monday
            "tuesday" -> tuesday
            "wednesday" -> wednesday
            "thursday" -> thursday
            "friday" -> friday
            "saturday" -> saturday
            "sunday" -> sunday
            else -> Statuses.OFF_OTHER
        }
    }
}
