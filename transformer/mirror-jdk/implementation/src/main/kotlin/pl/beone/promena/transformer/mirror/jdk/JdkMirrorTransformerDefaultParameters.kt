package pl.beone.promena.transformer.mirror.jdk

import java.time.Duration

data class JdkMirrorTransformerDefaultParameters(
    val sleep: Long? = null,
    val throwException: Boolean,
    val timeout: Duration? = null
)