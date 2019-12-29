package pl.beone.promena.transformer.appender.jdkspecific.configuration

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformer
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformerDefaultParameters
import javax.annotation.PostConstruct

@Configuration
class JdkSpecificAppenderTransformerLogger(
    private val defaultParameters: JdkSpecificAppenderTransformerDefaultParameters
) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @PostConstruct
    private fun log() {
        logger.info {
            "Run <${JdkSpecificAppenderTransformer::class.java.canonicalName}> with <$defaultParameters>"
        }
    }
}