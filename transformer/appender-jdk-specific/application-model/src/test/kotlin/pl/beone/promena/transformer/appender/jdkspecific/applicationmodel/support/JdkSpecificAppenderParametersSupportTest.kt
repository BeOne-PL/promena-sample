package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.support

import io.kotlintest.shouldNotThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderSupport.ParametersSupport.isSupported
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.jdkSpecificAppenderParameters
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException

class JdkSpecificAppenderParametersSupportTest {

    @Test
    fun `isSupported _ all parameters`() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(jdkSpecificAppenderParameters(chars = "$"))
        }
    }
}