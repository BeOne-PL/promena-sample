package pl.beone.promena.transformer.mirror.jdk.applicationmodel.support

import io.mockk.*
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.dataDescriptor
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorSupport.MediaTypeSupport
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorSupport.ParametersSupport
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorSupport.isSupported

class JdkMirrorSupportTest {

    @Test
    fun isSupported() {
        val mediaType = mockk<MediaType>()
        val dataDescriptor = dataDescriptor(singleDataDescriptor(mockk(), mediaType, mockk()))
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        mockkStatic(MediaTypeSupport::class)
        mockkStatic(ParametersSupport::class)
        every { MediaTypeSupport.isSupported(mediaType, targetMediaType) } just Runs
        every { ParametersSupport.isSupported(parameters) } just Runs

        isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { MediaTypeSupport.isSupported(mediaType, targetMediaType) }
        verify(exactly = 1) { ParametersSupport.isSupported(parameters) }
    }
}