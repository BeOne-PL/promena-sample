package pl.beone.promena.transformer.mirror.jdk.util

import io.mockk.mockk
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.communication.CommunicationWritableDataCreator
import pl.beone.promena.transformer.contract.model.data.WritableData
import pl.beone.promena.transformer.internal.model.data.memory.emptyMemoryWritableData
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformer
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformerDefaultParameters

internal object MemoryCommunicationWritableDataCreator : CommunicationWritableDataCreator {
    override fun create(communicationParameters: CommunicationParameters): WritableData = emptyMemoryWritableData()
}

internal fun createJdkMirrorTransformer(
    parameters: JdkMirrorTransformerDefaultParameters = JdkMirrorTransformerDefaultParameters(throwException = false)
): JdkMirrorTransformer =
    JdkMirrorTransformer(parameters, mockk(), MemoryCommunicationWritableDataCreator)