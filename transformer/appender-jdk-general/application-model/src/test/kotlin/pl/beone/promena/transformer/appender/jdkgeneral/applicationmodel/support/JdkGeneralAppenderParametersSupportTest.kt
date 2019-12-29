package pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.support

import io.kotlintest.shouldNotThrow
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.JdkGeneralAppenderSupport.ParametersSupport.isSupported
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.jdkGeneralAppenderParameters
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException

class JdkGeneralAppenderParametersSupportTest {

    @Test
    fun `isSupported _ all parameters`() {
        shouldNotThrow<TransformationNotSupportedException> {
            isSupported(jdkGeneralAppenderParameters(chars = "$"))
        }
    }
}