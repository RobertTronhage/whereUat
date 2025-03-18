package se.tronhage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import se.tronhage.Type.Status
import se.tronhage.Type.User
import java.time.LocalDate

@Repository
interface StatusRepository : JpaRepository<Status, Long> {
    fun findByUserAndDate(user: User, date: LocalDate): Status?
}