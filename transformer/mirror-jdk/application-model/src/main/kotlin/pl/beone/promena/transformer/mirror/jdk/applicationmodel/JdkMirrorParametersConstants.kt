package pl.beone.promena.transformer.mirror.jdk.applicationmodel

object JdkMirrorParametersConstants {

    object Sleep {
        const val NAME = "sleep"
        @JvmField
        val CLASS = Long::class.java
    }

    object ThrowException {
        const val NAME = "throwException"
        @JvmField
        val CLASS = Boolean::class.java
    }
}