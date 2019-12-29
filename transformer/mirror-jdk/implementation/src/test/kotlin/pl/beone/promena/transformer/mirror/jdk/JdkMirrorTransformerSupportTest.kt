package pl.beone.promena.transformer.mirror.jdk

import io.mockk.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorSupport
import pl.beone.promena.transformer.mirror.jdk.util.createJdkMirrorTransformer

@ExtendWith(DockerExtension::class)
class JdkMirrorTransformerSupportTest {

    @Test
    fun isSupported() {
        val dataDescriptor = mockk<DataDescriptor>()
        val targetMediaType = mockk<MediaType>()
        val parameters = mockk<Parameters>()

        mockkStatic(JdkMirrorSupport::class)
        every { JdkMirrorSupport.isSupported(dataDescriptor, targetMediaType, parameters) } just Runs

        createJdkMirrorTransformer()
            .isSupported(dataDescriptor, targetMediaType, parameters)

        verify(exactly = 1) { JdkMirrorSupport.isSupported(dataDescriptor, targetMediaType, parameters) }
    }
}