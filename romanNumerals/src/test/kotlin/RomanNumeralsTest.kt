
import org.hamcrest.core.Is
import org.junit.Assert.assertThat
import org.junit.Test
import roman.*

/**
 * Created by skramer on 7/25/16.
 * - single values, ROMAN_ONE, V, X, L, C, D, M
 * - concatenated single values II, III, XX, DD
 * - illegal concatenations IIII, XXXX,
 * - mixed simple additive concatenations XI, VI, LX
 * - mixed simple subtractive concatenations IX, XL
 * - illegal subtractive concatenations e.g. IM, XXXL
 * - acceptance test: 1999
 * - boundary values: 1, 3000, 1000, 666, 999, 888
 * - case sensitivity
 */

class RomanNumeralsTest {

    private val ROMAN_UNKNOWN: String = "Z"

    @Test(expected = IllegalArgumentException::class)
    fun unknownLetterThrowsException() {
        romanToDecimal(ROMAN_UNKNOWN)
    }

    @Test
    fun numericalValueOfSingleLetterIIsRecognized() {
        assertThat(romanToDecimal(ROMAN_ONE), Is.`is`(1))
    }

    @Test
    fun numericalValueOfSingleLetterVIsRecognized() {
        assertThat(romanToDecimal(ROMAN_FIVE), Is.`is`(5))
    }

    @Test
    fun numericalValueOfSingleLetterXIsRecognized() {
        assertThat(romanToDecimal(ROMAN_TEN), Is.`is`(10))
    }

    @Test
    fun numericalValueOfSingleLetterLIsRecognized() {
        assertThat(romanToDecimal(ROMAN_FIFTY), Is.`is`(50))
    }

    @Test
    fun numericalValueOfSingleLetterCIsRecognized() {
        assertThat(romanToDecimal(ROMAN_HUNDRED), Is.`is`(100))
    }
}

