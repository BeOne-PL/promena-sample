package pl.beone.promena.transformer.appender.jdkgeneral

import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.JdkGeneralAppenderSupport
import pl.beone.promena.transformer.appender.jdkgeneral.util.createJdkGeneralAppenderTransformer
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters

@ExtendWith(DockerExtension::class)
class JdkGeneralAppenderTransformerSupportTest {

    @Test
    fun isSupported() {
        val dataDescriptor = mockk<DataDescriptor>()
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        mockkStatic(JdkGeneralAppenderSupport::class)
        every { JdkGeneralAppenderSupport.isSupported(dataDescriptor, targetMediaType, parameters) } just Runs

        createJdkGeneralAppenderTransformer()
            .isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { JdkGeneralAppenderSupport.isSupported(dataDescriptor, targetMediaType, parameters) }
    }
}