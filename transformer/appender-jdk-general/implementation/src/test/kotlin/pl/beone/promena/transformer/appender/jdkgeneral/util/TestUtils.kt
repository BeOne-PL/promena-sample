package pl.beone.promena.transformer.appender.jdkgeneral.util

import io.mockk.mockk
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformer
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.communication.CommunicationWritableDataCreator
import pl.beone.promena.transformer.contract.model.data.WritableData
import pl.beone.promena.transformer.internal.model.data.memory.emptyMemoryWritableData

internal object MemoryCommunicationWritableDataCreator : CommunicationWritableDataCreator {
    override fun create(communicationParameters: CommunicationParameters): WritableData = emptyMemoryWritableData()
}

internal fun createJdkGeneralAppenderTransformer(
    parameters: JdkGeneralAppenderTransformerDefaultParameters = JdkGeneralAppenderTransformerDefaultParameters()
): JdkGeneralAppenderTransformer =
    JdkGeneralAppenderTransformer(parameters, mockk(), MemoryCommunicationWritableDataCreator)