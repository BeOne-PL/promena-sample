package pl.beone.promena.transformer.appender.jdkspecific.example

import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.jdkSpecificAppenderParameters
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.jdkSpecificAppenderTransformation
import pl.beone.promena.transformer.contract.transformation.Transformation

fun promena(): Transformation {
    // Data: example.txt

    return jdkSpecificAppenderTransformation(jdkSpecificAppenderParameters(chars = "$"))
}