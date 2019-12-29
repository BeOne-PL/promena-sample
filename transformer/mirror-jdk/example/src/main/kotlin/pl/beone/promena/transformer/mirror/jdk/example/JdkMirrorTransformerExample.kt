package pl.beone.promena.transformer.mirror.jdk.example

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.contract.transformation.next
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.jdkMirrorParameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.jdkMirrorTransformation

fun promena(): Transformation {
    // Data: example.txt

    return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters())
}

fun `promena _ sleep`(): Transformation {
    // Data: example.txt

    return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(sleep = 15000))
}

fun `promena _ throw exception`(): Transformation {
    // Data: example.txt

    return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(throwException = true))
}

fun `promena _ composite transformation`(): Transformation {
    // Data: example.txt

    return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(sleep = 2000)) next
            jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(sleep = 3000))
}