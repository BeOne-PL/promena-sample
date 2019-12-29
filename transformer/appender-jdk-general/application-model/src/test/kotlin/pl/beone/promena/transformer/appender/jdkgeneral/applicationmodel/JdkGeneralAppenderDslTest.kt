package pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.JdkGeneralAppenderConstants.TRANSFORMER_ID
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN

class JdkGeneralAppenderDslTest {

    @Test
    fun jdkGeneralAppenderTransformation() {
        with(jdkGeneralAppenderTransformation(jdkGeneralAppenderParameters(chars = "$"))) {
            transformerId shouldBe TRANSFORMER_ID
            targetMediaType shouldBe TEXT_PLAIN
            parameters.getAll().size shouldBe 1
        }
    }
}