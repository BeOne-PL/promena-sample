package pl.beone.promena.transformer.appender.jdkgeneral.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformer
import pl.beone.promena.transformer.appender.jdkgeneral.JdkGeneralAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.communication.CommunicationWritableDataCreator

@Configuration
class JdkGeneralAppenderTransformerContext {

    @Bean
    fun jdkGeneralAppenderTransformer(
        defaultParameters: JdkGeneralAppenderTransformerDefaultParameters,
        @Qualifier("internalCommunicationParameters") communicationParameters: CommunicationParameters,
        @Qualifier("internalCommunicationWritableDataCreator") communicationWritableDataCreator: CommunicationWritableDataCreator
    ) =
        JdkGeneralAppenderTransformer(
            defaultParameters,
            communicationParameters,
            communicationWritableDataCreator
        )
}