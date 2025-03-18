package se.tronhage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import se.tronhage.Type.Routine
import se.tronhage.Type.User

@Repository
interface RoutineRepository : JpaRepository<Routine, Long> {

}