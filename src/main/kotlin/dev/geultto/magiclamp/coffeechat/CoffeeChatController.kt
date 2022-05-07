package dev.geultto.magiclamp.coffeechat

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import dev.geultto.magiclamp.slack.SlackService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CoffeeChatController(private val slackService: SlackService) {

    @GetMapping("/hello")
    fun hello(): String {
        return "WORLD!"
    }

    @PostMapping("/ping")
    fun ping(): ResponseEntity<String> {
        slackService.pingPong()
        return ResponseEntity.ok().build()
    }
}
