package pl.beone.promena.transformer.appender.jdkspecific

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.communication.file.model.internal.fileCommunicationParameters
import pl.beone.promena.communication.memory.model.internal.memoryCommunicationParameters
import pl.beone.promena.transformer.appender.jdkspecific.util.test
import pl.beone.promena.transformer.internal.model.data.file.FileData
import pl.beone.promena.transformer.internal.model.data.file.toFileData
import pl.beone.promena.transformer.internal.model.data.memory.MemoryData
import pl.beone.promena.transformer.internal.model.data.memory.toMemoryData

@ExtendWith(DockerExtension::class)
class JdkSpecificAppenderTransformerTest {

    @Test
    fun transform_memoryData() {
        test(memoryCommunicationParameters(), MemoryData::class) {
            it.toMemoryData()
        }
    }

    @Test
    fun transform_fileData() {
        val directory = createTempDir()
        test(fileCommunicationParameters(directory), FileData::class) { content ->
            createTempFile(directory = directory)
                .also { it.appendText(content) }
                .toFileData()
        }
    }
}