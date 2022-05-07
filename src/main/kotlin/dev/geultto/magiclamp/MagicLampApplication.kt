package dev.geultto.magiclamp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@EnableConfigurationProperties(SlackProperties::class)
@SpringBootApplication
class MagicLampApplication

fun main(args: Array<String>) {
    runApplication<MagicLampApplication>(*args)
}
