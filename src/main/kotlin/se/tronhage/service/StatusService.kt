package se.tronhage.service

import org.springframework.stereotype.Service
import se.tronhage.Type.Status
import se.tronhage.Type.User
import se.tronhage.enum.Statuses
import se.tronhage.repository.RoutineRepository
import se.tronhage.repository.StatusRepository
import se.tronhage.repository.UserRepository
import java.time.LocalDate

@Service
class StatusService(
    private val userRepository: UserRepository,
    private val statusRepository: StatusRepository,
    private val routineRepository: RoutineRepository
) {
    fun setStatus(slackId: String, date: LocalDate, status: Statuses) {
        val user = userRepository.findAll().find { it.slackId == slackId } ?: return
        statusRepository.save(Status(null, user, date, status))
    }

    fun getStatusSummary(date: LocalDate): Map<Statuses, List<String>> {
        return statusRepository.findAll()
            .filter { it.date == date }
            .groupBy { it.status }
            .mapValues { it.value.map { s -> s.user.name } }
    }

    fun getMissingUsers(date: LocalDate): List<User> {
        return userRepository.findAll().filter { user ->
            statusRepository.findByUserAndDate(user, date) == null
        }
    }
}