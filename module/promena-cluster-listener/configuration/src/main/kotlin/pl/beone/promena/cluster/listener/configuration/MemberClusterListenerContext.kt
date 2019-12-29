package pl.beone.promena.cluster.listener.configuration

import akka.actor.ActorSystem
import akka.actor.Props
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.beone.promena.cluster.listener.MemberClusterListener

@Configuration
class MemberClusterListenerContext {

    @Bean
    fun memberClusterListener(
        actorSystem: ActorSystem
    ) =
        actorSystem.actorOf(
            Props.create(MemberClusterListener::class.java) { MemberClusterListener() },
            "memberClusterListener"
        )!!
}