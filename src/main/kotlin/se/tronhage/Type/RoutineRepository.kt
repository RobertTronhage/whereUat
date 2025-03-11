package se.tronhage.Type

import org.springframework.data.jpa.repository.JpaRepository

interface RoutineRepository : JpaRepository<Routine, String> {

}