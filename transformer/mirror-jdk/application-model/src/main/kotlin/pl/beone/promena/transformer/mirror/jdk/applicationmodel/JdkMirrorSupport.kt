package pl.beone.promena.transformer.mirror.jdk.applicationmodel

import pl.beone.lib.typeconverter.applicationmodel.exception.TypeConversionException
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorParametersConstants.Sleep
import pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorParametersConstants.ThrowException

object JdkMirrorSupport {

    @JvmStatic
    fun isSupported(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters) {
        dataDescriptor.descriptors.forEach { (_, mediaType) -> MediaTypeSupport.isSupported(mediaType, targetMediaType) }
        ParametersSupport.isSupported(parameters)
    }

    object MediaTypeSupport {
        @JvmStatic
        fun isSupported(mediaType: MediaType, targetMediaType: MediaType) {
            if (mediaType != targetMediaType) {
                throw TransformationNotSupportedException.custom("Media type and target media type must be same")
            }
        }
    }

    object ParametersSupport {
        @JvmStatic
        fun isSupported(parameters: Parameters) {
            parameters.validate(Sleep.NAME, Sleep.CLASS, false, "<0, ${Long.MAX_VALUE}>") { (0..Long.MAX_VALUE).contains(it) }
            parameters.validate(ThrowException.NAME, ThrowException.CLASS, false)
        }

        private fun <T> Parameters.validate(
            name: String,
            clazz: Class<T>,
            mandatory: Boolean,
            valueVerifierMessage: String? = null,
            valueVerifier: (T) -> Boolean = { true }
        ) {
            try {
                val value = get(name, clazz)
                if (!valueVerifier(value)) {
                    throw TransformationNotSupportedException.unsupportedParameterValue(name, value, valueVerifierMessage)
                }
            } catch (e: NoSuchElementException) {
                if (mandatory) {
                    throw TransformationNotSupportedException.mandatoryParameter(name)
                }
            } catch (e: TypeConversionException) {
                throw TransformationNotSupportedException.unsupportedParameterType(name, clazz)
            }
        }
    }
}