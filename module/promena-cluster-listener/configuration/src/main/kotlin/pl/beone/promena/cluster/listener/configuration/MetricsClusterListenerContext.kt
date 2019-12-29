package pl.beone.promena.cluster.listener.configuration

import akka.actor.ActorSystem
import akka.actor.Props
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.cluster.listener.MetricsClusterListener

@Configuration
class MetricsClusterListenerContext {

    @Bean
    fun metricsClusterListener(
        actorSystem: ActorSystem
    ) =
        actorSystem.actorOf(
            Props.create(MetricsClusterListener::class.java) { MetricsClusterListener() },
            "metricsClusterListener"
        )!!
}