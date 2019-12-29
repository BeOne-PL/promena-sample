@file:JvmName("JdkGeneralAppenderDsl")

package pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel

import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.JdkGeneralAppenderConstants.TRANSFORMER_ID
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.JdkGeneralAppenderConstants.TRANSFORMER_NAME
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.contract.transformation.singleTransformation

fun appenderTransformation(targetMediaType: MediaType, parameters: Parameters): Transformation.Single =
    singleTransformation(TRANSFORMER_NAME, targetMediaType, parameters)

fun jdkGeneralAppenderTransformation(parameters: Parameters): Transformation.Single =
    singleTransformation(TRANSFORMER_ID, TEXT_PLAIN, parameters)