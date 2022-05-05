package dev.geultto.magiclamp

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "slack")
data class SampleProperties (val token: String)
