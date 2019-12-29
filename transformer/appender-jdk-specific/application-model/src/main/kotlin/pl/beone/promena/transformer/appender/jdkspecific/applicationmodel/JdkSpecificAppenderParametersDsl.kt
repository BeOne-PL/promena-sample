@file:JvmName("JdkSpecificAppenderParametersDsl")

package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel

import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderParametersConstants.Chars
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import pl.beone.promena.transformer.internal.model.parameters.plus

fun jdkSpecificAppenderParameters(chars: String): MapParameters =
    emptyParameters() +
            (Chars.NAME to chars)

fun Parameters.getChars(): String =
    get(Chars.NAME, Chars.CLASS)