package pl.beone.promena.transformer.mirror.jdk.example;

import pl.beone.promena.transformer.contract.transformation.Transformation;
import pl.beone.promena.transformer.contract.transformation.TransformationBuilder;

import static pl.beone.promena.transformer.applicationmodel.mediatype.MediaTypeConstants.TEXT_PLAIN;
import static pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorDsl.jdkMirrorTransformation;
import static pl.beone.promena.transformer.mirror.jdk.applicationmodel.JdkMirrorParametersDsl.jdkMirrorParameters;

public class JdkMirrorTransformerJavaExample {

    public static Transformation promena() {
        // Data: example.txt

        return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(null, null));
    }

    public static Transformation promena_sleep() {
        // Data: example.txt

        return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(3000L, null));
    }

    public static Transformation promena_throwException() {
        // Data: example.txt

        return jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(null, true));
    }

    public static Transformation promena_compositeTransformation() {
        // Data: example.txt

        return new TransformationBuilder()
                .next(jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(2000L, null)))
                .next(jdkMirrorTransformation(TEXT_PLAIN, jdkMirrorParameters(3000L, null)))
                .build();
    }
}
