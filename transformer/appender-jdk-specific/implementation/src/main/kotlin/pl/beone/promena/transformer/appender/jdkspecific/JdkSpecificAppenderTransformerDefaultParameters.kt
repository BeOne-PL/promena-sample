package pl.beone.promena.transformer.appender.jdkspecific

import java.time.Duration

data class JdkSpecificAppenderTransformerDefaultParameters(
    val timeout: Duration? = null
)