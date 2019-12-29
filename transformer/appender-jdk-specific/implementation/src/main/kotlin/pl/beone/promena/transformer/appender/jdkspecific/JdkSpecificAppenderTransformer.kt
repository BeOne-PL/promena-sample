package pl.beone.promena.transformer.appender.jdkspecific

import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderSupport
import pl.beone.promena.transformer.appender.jdkspecific.processor.Processor
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.Transformer
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.data.TransformedDataDescriptor
import pl.beone.promena.transformer.contract.data.toTransformedDataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters

class JdkSpecificAppenderTransformer(
    defaultParameters: JdkSpecificAppenderTransformerDefaultParameters,
    private val communicationParameters: CommunicationParameters
) : Transformer {

    private val processor = Processor(defaultParameters, communicationParameters)

    override fun transform(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters): TransformedDataDescriptor =
        dataDescriptor.descriptors
            .map { processor.process(it, parameters) }
            .toTransformedDataDescriptor()

    override fun isSupported(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters) {
        JdkSpecificAppenderSupport.isSupported(dataDescriptor, targetMediaType, parameters)
    }
}