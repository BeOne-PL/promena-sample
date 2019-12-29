package pl.beone.promena.transformer.appender.jdkspecific.applicationmodel

import pl.beone.lib.typeconverter.applicationmodel.exception.TypeConversionException
import pl.beone.promena.transformer.appender.jdkspecific.applicationmodel.JdkSpecificAppenderParametersConstants.Chars
import pl.beone.promena.transformer.applicationmodel.exception.transformer.TransformationNotSupportedException
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaType
import pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN
import pl.beone.promena.transformer.contract.data.DataDescriptor
import pl.beone.promena.transformer.contract.model.Parameters

object JdkSpecificAppenderSupport {

    @JvmStatic
    fun isSupported(dataDescriptor: DataDescriptor, targetMediaType: MediaType, parameters: Parameters) {
        dataDescriptor.descriptors.forEach { (_, mediaType) -> MediaTypeSupport.isSupported(mediaType, targetMediaType) }
        ParametersSupport.isSupported(parameters)
    }

    object MediaTypeSupport {
        private val supportedMediaType = setOf(
            TEXT_PLAIN to TEXT_PLAIN
        )

        @JvmStatic
        fun isSupported(mediaType: MediaType, targetMediaType: MediaType) {
            if (!supportedMediaType.contains(mediaType to targetMediaType)) {
                throw TransformationNotSupportedException.unsupportedMediaType(mediaType, targetMediaType)
            }
        }
    }

    object ParametersSupport {
        @JvmStatic
        fun isSupported(parameters: Parameters) {
            parameters.validate(Chars.NAME, Chars.CLASS, true)
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