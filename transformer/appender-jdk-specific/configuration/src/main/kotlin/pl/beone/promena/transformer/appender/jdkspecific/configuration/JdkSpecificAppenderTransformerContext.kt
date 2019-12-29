package pl.beone.promena.transformer.appender.jdkspecific.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformer
import pl.beone.promena.transformer.appender.jdkspecific.JdkSpecificAppenderTransformerDefaultParameters
import pl.beone.promena.transformer.contract.communication.CommunicationParameters

@Configuration
class JdkSpecificAppenderTransformerContext {

    @Bean
    fun jdkSpecificAppenderTransformer(
        defaultParameters: JdkSpecificAppenderTransformerDefaultParameters,
        @Qualifier("internalCommunicationParameters") communicationParameters: CommunicationParameters
    ) =
        JdkSpecificAppenderTransformer(
            defaultParameters,
            communicationParameters
        )
}