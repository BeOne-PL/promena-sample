package pl.beone.promena.transformer.mirror.jdk.configuration

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.mock.env.MockEnvironment
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformerDefaultParameters
import java.time.Duration

class JdkMirrorTransformerConfigurationContextTest {

    @Test
    fun `setting context _ default`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.mirror.jdk.default.parameters.sleep" to "",
                "transformer.pl.beone.promena.transformer.mirror.jdk.default.parameters.throwException" to "false",
                "transformer.pl.beone.promena.transformer.mirror.jdk.default.parameters.timeout" to ""
            )
        )

        val applicationContext = createConfigApplicationContext(environment, JdkMirrorTransformerConfigurationContext::class.java)
        with(applicationContext.getBean(JdkMirrorTransformerDefaultParameters::class.java)) {
            sleep shouldBe null
            throwException shouldBe false
            timeout shouldBe null
        }
    }

    @Test
    fun `setting context _ all`() {
        val environment = createEnvironment(
            mapOf(
                "transformer.pl.beone.promena.transformer.mirror.jdk.default.parameters.sleep" to "500",
                "transformer.pl.beone.promena.transformer.mirror.jdk.default.parameters.throwException" to "false",
                "transformer.pl.beone.promena.transformer.mirror.jdk.default.parameters.timeout" to "5m"
            )
        )

        val applicationContext = createConfigApplicationContext(environment, JdkMirrorTransformerConfigurationContext::class.java)
        with(applicationContext.getBean(JdkMirrorTransformerDefaultParameters::class.java)) {
            sleep shouldBe 500
            throwException shouldBe false
            timeout shouldBe Duration.ofMinutes(5)
        }
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