@file:JvmName("JdkMirrorParametersDsl")

package pl.beone.promena.transformer.mirror.jdk.applicationmodel

import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.internal.model.parameters.MapParameters
import pl.beone.promena.transformer.internal.model.parameters.addIfNotNull
import pl.beone.promena.transformer.internal.model.parameters.emptyParameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorParametersConstants.Sleep
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorParametersConstants.ThrowException

fun jdkMirrorParameters(sleep: Long? = null, throwException: Boolean? = null): MapParameters =
    emptyParameters() addIfNotNull
            (Sleep.NAME to sleep) addIfNotNull
            (ThrowException.NAME to throwException)

fun Parameters.getSleep(): Long =
    get(Sleep.NAME, Sleep.CLASS)

fun Parameters.getSleepOrNull(): Long? =
    getOrNull(Sleep.NAME, Sleep.CLASS)

fun Parameters.getSleepOrDefault(default: Long): Long =
    getOrDefault(Sleep.NAME, Sleep.CLASS, default)

fun Parameters.getThrowException(): Boolean =
    get(ThrowException.NAME, ThrowException.CLASS)

fun Parameters.getThrowExceptionOrNull(): Boolean? =
    getOrNull(ThrowException.NAME, ThrowException.CLASS)

fun Parameters.getThrowExceptionOrDefault(default: Boolean): Boolean =
    getOrDefault(ThrowException.NAME, ThrowException.CLASS, default)