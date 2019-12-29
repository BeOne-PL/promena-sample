package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel

import pl.beone.promena.transformer.contract.transformer.transformerId

object JdkSpecificAppenderConstants {

    const val TRANSFORMER_NAME = "appender"

    const val TRANSFORMER_SUB_NAME = "JDK specific"

    @JvmField
    val TRANSFORMER_ID = transformerId(TRANSFORMER_NAME, TRANSFORMER_SUB_NAME)
}