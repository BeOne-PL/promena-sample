@file:JvmName("JdkMirrorDsl")

package pl.beone.promena.transformer.mirror.jdk.applicationmodel

import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.contract.transformation.Transformation
import pl.beone.promena.transformer.contract.transformation.singleTransformation
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorConstants.TRANSFORMER_ID
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorConstants.TRANSFORMER_NAME

fun mirrorTransformation(targetMediaType: MediaType, parameters: Parameters): Transformation.Single =
    singleTransformation(TRANSFORMER_NAME, targetMediaType, parameters)

fun jdkMirrorTransformation(targetMediaType: MediaType, parameters: Parameters): Transformation.Single =
    singleTransformation(TRANSFORMER_ID, targetMediaType, parameters)