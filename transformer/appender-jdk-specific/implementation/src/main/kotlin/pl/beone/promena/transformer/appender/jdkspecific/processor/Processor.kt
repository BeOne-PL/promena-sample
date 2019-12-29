package pl.beone.promena.transformer.appender.jdkspecific.processor

import kotlinx.coroutines.asCoroutineDispatcher
import pl.beone.promena.communication.file.model.contract.FileCommunicationParametersConstants
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.getChars
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.singleTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.model.data.Data
import pl.beone.promena.transformer.internal.model.data.file.fileData
import pl.beone.promena.transformer.internal.model.data.memory.memoryData
import pl.beone.promena.transformer.util.execute
import java.io.File
import java.util.concurrent.Executors

internal class Processor(
    private val defaultParameters: JdkSpecificAppenderTransformerDefaultParameters,
    private val communicationParameters: CommunicationParameters
) {

    private val executionDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    fun process(singleDataDescriptor: DataDescriptor.Single, parameters: Parameters): TransformedDataDescriptor.Single {
        val (data, _, metadata) = singleDataDescriptor

        val transformedData = execute(parameters.getTimeoutOrNull() ?: defaultParameters.timeout, executionDispatcher) {
            when (communicationParameters.getId()) {
                FileCommunicationParametersConstants.ID -> appendCharsInFile(data, parameters)
                else -> appendCharsInMemory(data, parameters)
            }
        }

        return singleTransformedDataDescriptor(transformedData, metadata)
    }

    private fun appendCharsInFile(data: Data, parameters: Parameters): Data =
        fileData(
            File(data.getLocation())
                .also { it.appendText(parameters.getChars()) }
        )

    private fun appendCharsInMemory(data: Data, parameters: Parameters): Data =
        memoryData(
            (String(data.getBytes()) + parameters.getChars()).byteInputStream()
        )
}