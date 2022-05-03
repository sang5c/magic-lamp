package dev.geultto.magiclamp

import com.slack.api.Slack
import com.slack.api.methods.SlackApiException
import com.slack.api.methods.request.conversations.ConversationsListRequest.ConversationsListRequestBuilder
import com.slack.api.model.Conversation
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Test
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

import java.util.concurrent.ConcurrentMap


@Slf4j
class SampleTests {
    @Test
    fun testApi() {

        val slack = Slack.getInstance()
        val response = slack.methods().apiTest { it.foo("bar") }
        println(response)
    }

    private val conversationsStore: ConcurrentMap<String, Conversation> = ConcurrentHashMap()

    private fun saveConversations(conversations: List<Conversation>) {
        for (conversation in conversations) {
            conversationsStore[conversation.id] = conversation
        }
    }

    @Test
    fun testApi2() {
        // Initialize an API Methods client with the given token
        val client = Slack.getInstance().methods()
        try {
            // Call the conversations.list method using the built-in WebClient
            val result = client.conversationsList { r: ConversationsListRequestBuilder ->
                r // The token you used to initialize your app
                    .token(System.getenv("SLACK_BOT_TOKEN"))
            }
            saveConversations(result.channels)
        } catch (e: IOException) {
            println("error: " + e.message)
        } catch (e: SlackApiException) {
            println("error: " + e.message)
        }

        println("result : $conversationsStore")
    }
}
