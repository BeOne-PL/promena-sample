package pl.beone.promena.transformer.appender.jdkspecific.util

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.matchers.instanceOf
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformer
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.jdkSpecificAppenderParameters
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.singleDataDescriptor
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import kotlin.reflect.KClass

internal fun createJdkSpecificAppenderTransformer(
    communicationParameters: CommunicationParameters,
    parameters: JdkSpecificAppenderTransformerDefaultParameters = JdkSpecificAppenderTransformerDefaultParameters()
): JdkSpecificAppenderTransformer =
    JdkSpecificAppenderTransformer(parameters, communicationParameters)

internal fun test(communicationParameters: CommunicationParameters, dataClass: KClass<out Data>, convertToData: (String) -> Data) {
    val mediaType = MediaTypeConstants.TEXT_PLAIN
    val metadata = emptyMetadata()

    with(
        createJdkSpecificAppenderTransformer(communicationParameters = communicationParameters)
            .transform(
                singleDataDescriptor(convertToData("test"), mediaType, metadata),
                MediaTypeConstants.TEXT_PLAIN,
                jdkSpecificAppenderParameters(chars = "$")
            )
    ) {
        withClue("Transformed data should contain only <1> element") { descriptors shouldHaveSize 1 }

        with(descriptors[0]) {
            data.getBytes() shouldBe "test$".toByteArray()
            data shouldBe instanceOf(dataClass)
            this.metadata shouldBe metadata
        }
    }
}