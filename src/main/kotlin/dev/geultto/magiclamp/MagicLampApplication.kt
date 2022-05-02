package dev.geultto.magiclamp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MagicLampApplication

fun main(args: Array<String>) {
    runApplication<MagicLampApplication>(*args)
}
