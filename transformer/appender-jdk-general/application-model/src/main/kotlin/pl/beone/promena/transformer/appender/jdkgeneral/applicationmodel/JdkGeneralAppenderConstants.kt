package pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel

import pl.beone.promena.transformer.contract.transformer.transformerId

object JdkGeneralAppenderConstants {

    const val TRANSFORMER_NAME = "appender"

    const val TRANSFORMER_SUB_NAME = "JDK general"

    @JvmField
    val TRANSFORMER_ID = transformerId(TRANSFORMER_NAME, TRANSFORMER_SUB_NAME)
}