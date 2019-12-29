package pl.beone.promena.transformer.appender.jdkgeneral.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.appender.jdkgeneral.configuration.extension.getNotBlankProperty
import pl.beone.promena.transformer.appender.jdkgeneral.configuration.extension.toDuration

@Configuration
class JdkGeneralAppenderTransformerConfigurationContext {

    companion object {
        private const val PROPERTY_PREFIX = "transformer.pl.beone.promena.transformer.appender.jdkgeneral"
    }

    @Bean
    fun jdkGeneralAppenderTransformerDefaultParameters(environment: Environment): JdkGeneralAppenderTransformerDefaultParameters =
        JdkGeneralAppenderTransformerDefaultParameters(
            environment.getNotBlankProperty("$PROPERTY_PREFIX.default.parameters.timeout")?.toDuration()
        )
}