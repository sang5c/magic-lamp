package dev.geultto.magiclamp.slack

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "slack")
data class SlackProperties(val token: String)
