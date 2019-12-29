package pl.beone.promena.transformer.appender.jdkgeneral.processor

import kotlinx.coroutines.asCoroutineDispatcher
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.getChars
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.contract.model.data.WritableData
import pl.beone.promena.transformer.internal.extension.copy
import pl.beone.promena.transformer.util.execute
import java.util.concurrent.Executors

internal class Processor(
    private val defaultParameters: JdkGeneralAppenderTransformerDefaultParameters
) {

    private val executionDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    fun process(
        singleDataDescriptor: DataDescriptor.Single,
        parameters: Parameters,
        transformedWritableData: WritableData
    ): TransformedDataDescriptor.Single {
        val (data, _, metadata) = singleDataDescriptor

        execute(parameters.getTimeoutOrNull() ?: defaultParameters.timeout, executionDispatcher) {
            transformedWritableData.copy(appendChars(data, parameters).byteInputStream())
        }

        return singleTransformedDataDescriptor(transformedWritableData, metadata)
    }

    private fun appendChars(data: Data, parameters: Parameters): String =
        String(data.getBytes()) + parameters.getChars()
}