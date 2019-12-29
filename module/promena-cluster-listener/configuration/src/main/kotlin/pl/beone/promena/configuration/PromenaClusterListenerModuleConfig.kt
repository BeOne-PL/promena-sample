package pl.beone.promena.configuration

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ComponentScan(basePackages = ["pl.beone.promena.cluster.listener.configuration"])
@PropertySource("classpath:module-promena-cluster-listener.properties")
class PromenaClusterListenerModuleConfig