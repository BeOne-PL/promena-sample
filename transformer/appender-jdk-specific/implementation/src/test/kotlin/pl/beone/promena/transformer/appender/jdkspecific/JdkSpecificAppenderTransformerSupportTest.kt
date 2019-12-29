package pl.beone.promena.transformer.appender.jdkspecific

import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderSupport
import pl.beone.promena.transformer.appender.jdkspecific.util.createJdkSpecificAppenderTransformer

@ExtendWith(DockerExtension::class)
class JdkSpecificAppenderTransformerSupportTest {

    @Test
    fun isSupported() {
        val dataDescriptor = mockk<DataDescriptor>()
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        mockkStatic(JdkSpecificAppenderSupport::class)
        every { JdkSpecificAppenderSupport.isSupported(dataDescriptor, targetMediaType, parameters) } just Runs

        createJdkSpecificAppenderTransformer(communicationParameters = mockk())
            .isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { JdkSpecificAppenderSupport.isSupported(dataDescriptor, targetMediaType, parameters) }
    }
}