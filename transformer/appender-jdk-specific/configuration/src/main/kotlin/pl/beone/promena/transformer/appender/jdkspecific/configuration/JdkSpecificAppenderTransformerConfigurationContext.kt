package pl.beone.promena.transformer.appender.jdkspecific.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.appender.jdkspecific.configuration.extension.getNotBlankProperty
import pl.beone.promena.transformer.appender.jdkspecific.configuration.extension.toDuration

@Configuration
class JdkSpecificAppenderTransformerConfigurationContext {

    companion object {
        private const val PROPERTY_PREFIX = "transformer.pl.beone.promena.transformer.appender.jdkspecific"
    }

    @Bean
    fun jdkSpecificAppenderTransformerDefaultParameters(environment: Environment): JdkSpecificAppenderTransformerDefaultParameters =
        JdkSpecificAppenderTransformerDefaultParameters(
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.timeout")?.toDuration()
        )
}