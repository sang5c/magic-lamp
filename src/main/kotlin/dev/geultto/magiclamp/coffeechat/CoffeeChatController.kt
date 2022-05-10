package dev.geultto.magiclamp.coffeechat

import dev.geultto.magiclamp.slack.SlackService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CoffeeChatController(private val slackService: SlackService) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/hello")
    fun hello(): String {
        return "WORLD!"
    }

    @PostMapping("/ping")
    fun ping(body: Map<String, String>): ResponseEntity<String> {
        log.info("BODY: $body")
        slackService.pingPong()
        return ResponseEntity.ok().build()
    }
}
