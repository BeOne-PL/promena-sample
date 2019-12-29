package pl.beone.promena.transformer.mirror.jdk

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.numerics.shouldBeGreaterThan
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowExactly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import pl.beone.lib.junit.jupiter.external.DockerExtension
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.internal.model.data.memory.toMemoryData
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.jdkMirrorParameters
import pl.beone.promena.transformer.mirror.jdk.model.Resource.Text.EXAMPLE
import pl.beone.promena.transformer.mirror.jdk.util.createJdkMirrorTransformer
import pl.beone.promena.transformer.mirror.jdk.util.getResourceAsBytes
import kotlin.system.measureTimeMillis

@ExtendWith(DockerExtension::class)
class JdkMirrorTransformerTest {

    companion object {
        private val mediaType = TEXT_PLAIN
        private val metadata = emptyMetadata()
        private val singleDataDescriptor = singleDataDescriptor(getResourceAsBytes(EXAMPLE).toMemoryData(), mediaType, metadata)
    }

    @Test
    fun transform() {
        test(jdkMirrorParameters())
    }

    @Test
    fun transform_sleep() {
        val sleep = 3000L
        withClue("Execution should last at least <${sleep}ms>") {
            measureTimeMillis {
                test(jdkMirrorParameters(sleep = sleep))
            } shouldBeGreaterThan sleep
        }
    }

    @Test
    fun transform_exception() {
        shouldThrowExactly<IllegalStateException> {
            test(jdkMirrorParameters(throwException = true))
        }.message shouldBe "Exception"
    }

    private fun test(parameters: Parameters) {
        with(
            createJdkMirrorTransformer()
                .transform(singleDataDescriptor, TEXT_PLAIN, parameters)
        ) {
            withClue("Transformed data should contain only <1> element") { descriptors shouldHaveSize 1 }

            with(descriptors[0]) {
                data.getBytes() shouldBe "example content".toByteArray()
                metadata shouldBe metadata
            }
        }
    }
}