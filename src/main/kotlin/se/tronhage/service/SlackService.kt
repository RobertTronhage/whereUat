package se.tronhage.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import se.tronhage.Type.User
import se.tronhage.enum.Statuses

@Service
class SlackService {
    private val webhookUrl = "YOUR_SLACK_WEBHOOK_URL"

    private val emojiMap = mapOf(
        Statuses.OFFICE to ":office:",
        Statuses.WORKING_FROM_HOME to ":house:",
        Statuses.OFF_SICK to ":face_with_thermometer:",
        Statuses.OFF_VACATION to ":palm_tree:",
        Statuses.OFF_PARENTAL_LEAVE to ":baby:",
        Statuses.OFF_OTHER to ":calendar:"
    )

    fun updateSlackStatus(user: User, status: Statuses) {
        val payload = mapOf(
            "text" to "${user.name} is now ${status.name.replace("_", " ")} ${emojiMap[status]}"
        )

        RestTemplate().postForObject(webhookUrl, payload, String::class.java)
    }
}