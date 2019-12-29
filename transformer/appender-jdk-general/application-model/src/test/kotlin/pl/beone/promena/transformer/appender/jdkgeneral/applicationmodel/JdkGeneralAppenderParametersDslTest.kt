package pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class JdkGeneralAppenderParametersDslTest {

    companion object {
        private const val chars = "$"
    }

    @Test
    fun `jdkGeneralAppenderParameters _ all parameters`() {
        with(
            jdkGeneralAppenderParameters(
                chars = chars
            )
        ) {
            getChars() shouldBe chars
        }
    }
}