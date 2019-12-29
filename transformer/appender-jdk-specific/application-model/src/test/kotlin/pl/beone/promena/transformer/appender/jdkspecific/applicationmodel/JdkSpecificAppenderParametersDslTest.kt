package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class JdkSpecificAppenderParametersDslTest {

    companion object {
        private const val chars = "$"
    }

    @Test
    fun `jdkSpecificAppenderParameters _ all parameters`() {
        with(
            jdkSpecificAppenderParameters(
                chars = chars
            )
        ) {
            getChars() shouldBe chars
        }
    }
}