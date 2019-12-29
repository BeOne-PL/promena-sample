@file:JvmName("JdkGeneralAppenderParametersDsl")

package pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel

import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.JdkGeneralAppenderParametersConstants.Chars
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import pl.beone.promena.transformer.internal.model.parameters.plus

fun jdkGeneralAppenderParameters(chars: String): MapParameters =
    emptyParameters() +
            (Chars.NAME to chars)

fun Parameters.getChars(): String =
    get(Chars.NAME, Chars.CLASS)