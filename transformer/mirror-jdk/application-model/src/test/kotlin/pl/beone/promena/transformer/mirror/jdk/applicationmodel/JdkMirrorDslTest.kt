package pl.beone.promena.transformer.mirror.jdk.applicationmodel

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorConstants.TRANSFORMER_ID

class JdkMirrorDslTest {

    @Test
    fun jdkMirrorTransformation() {
        with(jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters())) {
            transformerId shouldBe TRANSFORMER_ID
            targetMediaType shouldBe TEXT_PLAIN
            parameters.getAll().size shouldBe 0
        }
    }
}