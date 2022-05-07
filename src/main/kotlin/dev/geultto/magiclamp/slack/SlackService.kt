package dev.geultto.magiclamp.slack

import com.slack.api.Slack
import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.request.conversations.ConversationsListRequest
import com.slack.api.methods.request.conversations.ConversationsMembersRequest
import com.slack.api.methods.request.users.UsersInfoRequest
import org.springframework.stereotype.Service

@Service
class SlackService(private val slackProperties: SlackProperties) {

    val client: MethodsClient = Slack.getInstance().methods(slackProperties.token)

    fun findChannels(): MutableList<SlackChannel>? {
        val result = client.conversationsList { r: ConversationsListRequest.ConversationsListRequestBuilder ->
            r.token(slackProperties.token)
        }
        return result.channels.stream()
            .map { c -> SlackChannel(c.id, c.name) }
            .toList()
    }

    fun findMembers(): MutableList<String>? {
        val result = client.conversationsMembers { r: ConversationsMembersRequest.ConversationsMembersRequestBuilder ->
            r.channel("CSCB3M43G")
        }
        return result.members
    }

    fun findMemberName(userId: String): SlackUser {
        val userInfo = client.usersInfo { r: UsersInfoRequest.UsersInfoRequestBuilder ->
            r.user(userId)
        }.user

        return SlackUser(
            userInfo.id,
            userInfo.profile.displayName,
            userInfo.profile.realName
        )
    }

    fun pingPong() {
        // Initialize an API Methods client with the given token
        val result = client.chatPostMessage { r: ChatPostMessageRequest.ChatPostMessageRequestBuilder ->
            r.channel("CSCB3M43G")
                .text("pong")
                .token(System.getenv("SLACK_BOT_TOKEN"))
        }
        println(result.isOk)
    }
}
