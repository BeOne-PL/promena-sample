package pl.beone.promena.transformer.appender.jdkgeneral.configuration

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformer
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformerDefaultParameters
import javax.annotation.PostConstruct

@Configuration
class JdkGeneralAppenderTransformerLogger(
    private val defaultParameters: JdkGeneralAppenderTransformerDefaultParameters
) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @PostConstruct
    private fun log() {
        logger.info {
            "Run <${JdkGeneralAppenderTransformer::class.java.canonicalName}> with <$defaultParameters>"
        }
    }
}