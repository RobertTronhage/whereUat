package se.tronhage.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import se.tronhage.enum.Statuses
import se.tronhage.repository.RoutineRepository
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class NotificationService(private val statusService: StatusService, private val routineRepository: RoutineRepository) {
    @Scheduled(cron = "0 0 15 * * MON-FRI")
    fun remindUsers() {
        val tomorrow = getNextWorkingDay().name.toLowerCase()
        val users = routineRepository.findAll()
        users.forEach { routine ->
            if (routine.getRoutineForDay(tomorrow) == Statuses.OFF_OTHER) {
                sendSlackInteractiveMessage(routine.userId)
            }
        }
    }

    @Scheduled(cron = "0 0 8 * * MON")
    fun weeklySummary() {
        val today = LocalDate.now()
        val summary = statusService.getStatusSummary(today)
        sendSlackMessageToChannel("Weekly summary: $summary")
    }

    fun sendSlackInteractiveMessage(userId: Long) {
        println("Sending interactive message to user $userId with status options: ${Statuses.values().toList()}")
    }

    fun sendSlackMessageToChannel(message: String) {
        println("Sending message to Slack channel: $message")
    }

    private fun getNextWorkingDay(): DayOfWeek {
        var nextDay = LocalDate.now().plusDays(1).dayOfWeek
        while (nextDay == DayOfWeek.SATURDAY || nextDay == DayOfWeek.SUNDAY) {
            nextDay = nextDay.plus(1)
        }
        return nextDay
    }
}