package pl.beone.promena.transformer.mirror.jdk.processor

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.WritableData
import pl.beone.promena.transformer.internal.extension.copy
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformerDefaultParameters
import pl.beone.promena.transformer.util.execute
import java.util.concurrent.Executors

internal class Processor(
    private val defaultParameters: JdkMirrorTransformerDefaultParameters
) {

    private val executionDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    fun process(
        singleDataDescriptor: DataDescriptor.Single,
        parameters: Parameters,
        transformedWritableData: WritableData
    ): TransformedDataDescriptor.Single {
        val (data, _, metadata) = singleDataDescriptor

        execute(parameters.getTimeoutOrNull() ?: defaultParameters.timeout, executionDispatcher) {
            parameters.getOrNull("sleep", Long::class.java)?.let { delay(it) }

            if (parameters.getOrDefault("throwException", Boolean::class.java, defaultParameters.throwException)) {
                error("Exception")
            }

            transformedWritableData.copy(data.getInputStream())
        }

        return singleTransformedDataDescriptor(transformedWritableData, metadata)
    }
}