package se.tronhage.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import se.tronhage.Type.RoutineRepository
import java.time.DayOfWeek
import java.time.LocalDate

@Component
class ReminderService(val routineRepository: RoutineRepository, val slackService: SlackService) {

    @Scheduled(cron = "0 0 14 * * MON-FRI")
    fun sendReminder() {
        val users = routineRepository.findAll()
        users.forEach { routine ->
            val nextDay = getNextDay()
            if (nextDay != null && routine.getRoutineForDay(nextDay) == "Off") {
                slackService.setStatus(routine.userId, "Where are you working from tomorrow? " +
                        "Set a routine so that your co-workers know where you at!", ":memo:")
            }
        }
    }

    private fun getNextDay(): String? {
        val today = LocalDate.now()
        val nextDay = today.plusDays(1)

        return when (nextDay.dayOfWeek) {
            DayOfWeek.MONDAY -> "monday"
            DayOfWeek.TUESDAY -> "tuesday"
            DayOfWeek.WEDNESDAY -> "wednesday"
            DayOfWeek.THURSDAY -> "thursday"
            DayOfWeek.FRIDAY -> "friday"
            else -> getNextDay()
        }
    }
}