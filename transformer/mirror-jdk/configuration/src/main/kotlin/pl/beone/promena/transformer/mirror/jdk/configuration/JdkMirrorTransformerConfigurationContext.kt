package pl.beone.promena.transformer.mirror.jdk.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformerDefaultParameters
import pl.beone.promena.transformer.mirror.jdk.configuration.extension.getNotBlankProperty
import pl.beone.promena.transformer.mirror.jdk.configuration.extension.getRequiredNotBlankProperty
import pl.beone.promena.transformer.mirror.jdk.configuration.extension.toDuration

@Configuration
class JdkMirrorTransformerConfigurationContext {

    companion object {
        private const val PROPERTY_PREFIX = "transformer.pl.beone.promena.transformer.mirror.jdk"
    }

    @Bean
    fun jdkMirrorTransformerDefaultParameters(environment: Environment): JdkMirrorTransformerDefaultParameters =
        JdkMirrorTransformerDefaultParameters(
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.sleep")?.toLong(),
            environment.getRequiredNotBlankProperty("$PROPERTY_PREFIX.default.parameters.throwException").toBoolean(),
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.timeout")?.toDuration()
        )
}