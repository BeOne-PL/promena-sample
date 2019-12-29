@file:JvmName("JdkSpecificAppenderDsl")

package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel

import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderConstants.TRANSFORMER_ID
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderConstants.TRANSFORMER_NAME
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.contract.transformation.singleTransformation

fun appenderTransformation(targetMediaType: MediaType, parameters: Parameters): Transformation.Single =
    singleTransformation(TRANSFORMER_NAME, targetMediaType, parameters)

fun jdkSpecificAppenderTransformation(parameters: Parameters): Transformation.Single =
    singleTransformation(TRANSFORMER_ID, MediaTypeConstants.TEXT_PLAIN, parameters)