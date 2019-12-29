package pl.beone.promena.transformer.appender.jdkgeneral.example

import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.jdkGeneralAppenderParameters
import pl.beone.promena.transformer.appender.jdkgeneral.applicationmodel.jdkGeneralAppenderTransformation
import pl.beone.promena.transformer.contract.transformation.Transformation

fun promena(): Transformation {
    // Data: example.txt

    return jdkGeneralAppenderTransformation(jdkGeneralAppenderParameters(chars = "$"))
}