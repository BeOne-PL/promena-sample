package pl.beone.promena.transformer.mirror.jdk.configuration

import mu.KotlinLogging
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformer
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformerDefaultParameters
import javax.annotation.PostConstruct

@Configuration
class JdkMirrorTransformerLogger(
    private val defaultParameters: JdkMirrorTransformerDefaultParameters
) {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    @PostConstruct
    private fun log() {
        logger.info {
            "Run <${JdkMirrorTransformer::class.java.canonicalName}> with <$defaultParameters>"
        }
    }
}