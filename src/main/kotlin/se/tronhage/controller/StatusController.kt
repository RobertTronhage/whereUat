package se.tronhage.controller

import org.springframework.web.bind.annotation.*
import se.tronhage.enum.Statuses
import se.tronhage.service.StatusService
import java.time.LocalDate

@RestController
@RequestMapping("/status")
class StatusController(private val statusService: StatusService) {
    @PostMapping
    fun setStatus(@RequestParam slackId: String, @RequestParam date: String, @RequestParam status: Statuses) {
        statusService.setStatus(slackId, LocalDate.parse(date), status)
    }

    @GetMapping("/summary")
    fun getSummary(@RequestParam date: String): Map<Statuses, List<String>> {
        return statusService.getStatusSummary(LocalDate.parse(date))
    }
}