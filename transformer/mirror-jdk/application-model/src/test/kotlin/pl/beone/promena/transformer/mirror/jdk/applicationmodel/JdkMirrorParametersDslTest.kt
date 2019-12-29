package pl.beone.promena.transformer.mirror.jdk.applicationmodel

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test

class JdkMirrorParametersDslTest {

    companion object {
        private const val sleep = 500L
        private const val throwException = true
    }

    @Test
    fun `jdkMirrorParameters _ default parameters`() {
        with(jdkMirrorParameters()) {
            shouldThrow<NoSuchElementException> { getSleep() }
            getSleepOrNull() shouldBe null
            getSleepOrDefault(sleep) shouldBe sleep

            shouldThrow<NoSuchElementException> { getThrowException() }
            getThrowExceptionOrNull() shouldBe null
            getThrowExceptionOrDefault(throwException) shouldBe throwException
        }
    }

    @Test
    fun `jdkMirrorParameters _ all parameters`() {
        with(
            jdkMirrorParameters(
                sleep = sleep,
                throwException = throwException
            )
        ) {
            getSleep() shouldBe sleep
            getThrowException() shouldBe throwException
        }
    }
}