package pl.beone.promena.transformer.appender.jdkgeneral

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.jdkGeneralAppenderParameters
import pl.beone.promena.transformer.appender.jdkgeneral.util.createJdkGeneralAppenderTransformer
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.internal.model.data.memory.toMemoryData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata

@ExtendWith(DockerExtension::class)
class JdkGeneralAppenderTransformerTest {

    @Test
    fun transform() {
        val mediaType = TEXT_PLAIN
        val metadata = emptyMetadata()

        with(
            createJdkGeneralAppenderTransformer()
                .transform(
                    singleDataDescriptor("test".toMemoryData(), mediaType, metadata),
                    TEXT_PLAIN,
                    jdkGeneralAppenderParameters(chars = "$")
                )
        ) {
            withClue("Transformed data should contain only <1> element") { descriptors shouldHaveSize 1 }

            with(descriptors[0]) {
                data.getBytes() shouldBe "test$".toByteArray()
                this.metadata shouldBe metadata
            }
        }
    }
}