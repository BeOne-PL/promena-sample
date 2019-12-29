package pl.beone.promena.transformer.mirror.jdk.applicationmodel.support

import io.kotlintest.shouldBe
import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.APPLICATION_PDF
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorSupport.MediaTypeSupport.isSupported

class JdkMirrorMediaTypeSupportTest {

    @Test
    fun isSupported() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(TEXT_PLAIN, TEXT_PLAIN)
        }
    }

    @Test
    fun `isSupported _ different media type and target media type`() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(TEXT_PLAIN, APPLICATION_PDF)
        }.message shouldBe "Media type and target media type must be same"
    }
}