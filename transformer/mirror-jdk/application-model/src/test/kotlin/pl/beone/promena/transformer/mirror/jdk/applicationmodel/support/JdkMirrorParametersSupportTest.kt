package pl.beone.promena.transformer.mirror.jdk.applicationmodel.support

import io.kotlintest.shouldNotThrow
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorSupport.ParametersSupport.isSupported
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.jdkMirrorParameters

class JdkMirrorParametersSupportTest {

    @Test
    fun `isSupported _ default parameters`() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(jdkMirrorParameters())
        }
    }

    @Test
    fun `isSupported _ all parameters`() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(jdkMirrorParameters(500, true))
        }
    }

    @Test
    fun `isSupported _ sleep`() {
        shouldThrow<TransformationNotSupportedException> {
            isSupported(jdkMirrorParameters(-1, true))
        }
    }
}