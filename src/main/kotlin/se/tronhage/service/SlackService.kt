package se.tronhage.service

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import java.net.URI

@Service
class SlackService {

    private val slackToken = "TOKEN"
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
}