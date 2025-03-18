package se.tronhage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import se.tronhage.Type.User

@Repository
interface UserRepository : JpaRepository<User, Long>