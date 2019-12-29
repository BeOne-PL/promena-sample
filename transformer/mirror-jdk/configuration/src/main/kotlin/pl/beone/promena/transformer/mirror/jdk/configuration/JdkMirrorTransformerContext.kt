package pl.beone.promena.transformer.mirror.jdk.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.contract.communication.CommunicationParameters
import pl.beone.promena.transformer.contract.communication.CommunicationWritableDataCreator
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformer
import pl.beone.promena.transformer.mirror.jdk.JdkMirrorTransformerDefaultParameters

@Configuration
class JdkMirrorTransformerContext {

    @Bean
    fun jdkMirrorTransformer(
        defaultParameters: JdkMirrorTransformerDefaultParameters,
        @Qualifier("internalCommunicationParameters") communicationParameters: CommunicationParameters,
        @Qualifier("internalCommunicationWritableDataCreator") communicationWritableDataCreator: CommunicationWritableDataCreator
    ) =
        JdkMirrorTransformer(
            defaultParameters,
            communicationParameters,
            communicationWritableDataCreator
        )
}