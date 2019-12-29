package pl.beone.promena.transformer.mirror.jdk.applicationmodel

import pl.beone.promena.transformer.contract.transformer.transformerId

object JdkMirrorConstants {

    const val TRANSFORMER_NAME = "mirror"

    const val TRANSFORMER_SUB_NAME = "JDK"

    @JvmField
    val TRANSFORMER_ID = transformerId(TRANSFORMER_NAME, TRANSFORMER_SUB_NAME)
}