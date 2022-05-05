package dev.geultto.magiclamp

import com.slack.api.Slack
import com.slack.api.methods.request.chat.ChatPostMessageRequest.ChatPostMessageRequestBuilder
import com.slack.api.methods.request.conversations.ConversationsListRequest.ConversationsListRequestBuilder
import com.slack.api.methods.request.conversations.ConversationsMembersRequest.ConversationsMembersRequestBuilder
import com.slack.api.model.Conversation
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


@Slf4j
@ActiveProfiles("test")
@EnableConfigurationProperties(SampleProperties::class)
@SpringBootTest
class SampleTests(private val sampleProperties: SampleProperties) {

    @Test
    fun aa() {
        println(sampleProperties.token)
    }

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

    @DisplayName("채널 목록 조회")
    @Test
    fun testApi2() {
        // Initialize an API Methods client with the given token
        val client = Slack.getInstance().methods()
        // Call the conversations.list method using the built-in WebClient
        val result = client.conversationsList { r: ConversationsListRequestBuilder ->
            r // The token you used to initialize your app
                .token(System.getenv("SLACK_BOT_TOKEN"))
        }
        saveConversations(result.channels)

        println("result : $conversationsStore")
    }

    @DisplayName("사용자 목록 조회")
    @Test
    fun testApi3() {
        // Initialize an API Methods client with the given token
        val client = Slack.getInstance().methods()
        // Call the conversations.list method using the built-in WebClient
        val result = client.conversationsMembers { r: ConversationsMembersRequestBuilder ->
            r // The token you used to initialize your app
                .token(System.getenv("SLACK_BOT_TOKEN"))
                .channel("CSCB3M43G")
        }
        println(result.members) // 이곳에서 얻는 값은 멤버 식별자임: USA5B2R61 워크스페이스마다 값이 달라진다.
    }

    @DisplayName("메시지 전송")
    @Test
    fun testApi4() {
        // Initialize an API Methods client with the given token
        val client = Slack.getInstance().methods()
        val result = client.chatPostMessage { r: ChatPostMessageRequestBuilder ->
            r // The token you used to initialize your app
//                .token(token)
                .channel("CSCB3M43G")
                .text("hello world")
        } // You could also use a blocks[] array to send richer content

    }
}
