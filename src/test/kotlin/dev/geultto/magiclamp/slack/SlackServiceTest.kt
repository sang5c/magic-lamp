package dev.geultto.magiclamp.slack

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SlackServiceTest(@Autowired val slackService: SlackService) {

    @Test
    fun tt() {
        val findChannels = slackService.findChannels()
        findChannels?.forEach { c -> println(c) }
    }

    @Test
    fun findMembers() {
        val findMembers = slackService.findMembers()
        findMembers?.forEach { c -> println(c) }
    }

    @Test
    fun findMemberName() {
        val findMember = slackService.findMemberName("USA5B2R61")
        println(findMember)
    }
}
