package dev.geultto.magiclamp.coffeechat

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CoffeeChatController {

    @GetMapping("/hello")
    fun hello(): String {
        return "WORLD!"
    }
}
