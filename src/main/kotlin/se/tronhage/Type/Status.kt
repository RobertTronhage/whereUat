package se.tronhage.Type

import jakarta.persistence.*
import se.tronhage.enum.Statuses
import java.time.LocalDate

@Entity
@Table(name = "statuses")
data class Status(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    @ManyToOne val user: User,
    val date: LocalDate,
    @Enumerated(EnumType.STRING)
    val status: Statuses
)