package dev.geultto.magiclamp

import dev.geultto.magiclamp.slack.SlackProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(SlackProperties::class)
@SpringBootApplication
class MagicLampApplication

fun main(args: Array<String>) {
    runApplication<MagicLampApplication>(*args)
}
