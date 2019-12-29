package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderConstants.TRANSFORMER_ID
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN

class JdkSpecificAppenderDslTest {

    @Test
    fun jdkSpecificAppenderTransformation() {
        with(jdkSpecificAppenderTransformation(jdkSpecificAppenderParameters(chars = "$"))) {
            transformerId shouldBe TRANSFORMER_ID
            targetMediaType shouldBe TEXT_PLAIN
            parameters.getAll().size shouldBe 1
        }
    }
}