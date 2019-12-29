package pl.beone.promena.transformer.appender.jdkspecific.configuration

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.mock.env.MockEnvironment
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformerDefaultParameters
import java.time.Duration

class JdkSpecificAppenderTransformerConfigurationContextTest {

    @Test
    fun `setting context _ default`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.appender.jdkspecific.default.parameters.timeout" to ""
            )
        )

        createConfigApplicationContext(environment, JdkSpecificAppenderTransformerConfigurationContext::class.java)
            .getBean(JdkSpecificAppenderTransformerDefaultParameters::class.java).timeout shouldBe null
    }

    @Test
    fun `setting context _ all`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.appender.jdkspecific.default.parameters.timeout" to "5m"
            )
        )

        createConfigApplicationContext(environment, JdkSpecificAppenderTransformerConfigurationContext::class.java)
            .getBean(JdkSpecificAppenderTransformerDefaultParameters::class.java).timeout shouldBe Duration.ofMinutes(5)
    }

    private fun createEnvironment(properties: Map<String, String>): MockEnvironment =
        MockEnvironment()
            .apply { properties.forEach { (key, value) -> setProperty(key, value) } }

    private fun createConfigApplicationContext(environment: ConfigurableEnvironment, clazz: Class<*>): AnnotationConfigApplicationContext =
        AnnotationConfigApplicationContext().apply {
            this.environment = environment
            register(clazz)
            refresh()
        }
}