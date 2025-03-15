package se.tronhage.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import java.net.URI

@Service
class SlackService {
    @Value("\${slack.bot-token}")
    lateinit var slackToken: String

    @Value("\${slack.channel-id}")
    lateinit var slackChannelId: String

    private val restTemplate = RestTemplate()

    fun setStatus(userId: String, statusText: String, statusEmoji: String) {
        val url = "https://slack.com/api/users.profile.set"
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $slackToken")

        val body = mapOf(
                "user" to userId,
                "profile" to mapOf(
                        "status_text" to statusText,
                        "status_emoji" to statusEmoji
                )
        )

        val entity = RequestEntity(body, headers, HttpMethod.POST, URI(url))
        restTemplate.exchange(entity, String::class.java)
    }

    fun postUserStatusesToChannel(usersStatuses: Map<String, String>) {
        val url = "https://slack.com/api/chat.postMessage"
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer $slackToken")

        // Format message with all user statuses
        val statusesMessage = usersStatuses.map { (userId, status) ->
            "User $userId is working: $status"
        }.joinToString("\n")

        val body = mapOf(
                "channel" to slackChannelId,
                "text" to statusesMessage
        )

        val entity = RequestEntity(body, headers, HttpMethod.POST, URI(url))
        restTemplate.exchange(entity, String::class.java)
    }
}