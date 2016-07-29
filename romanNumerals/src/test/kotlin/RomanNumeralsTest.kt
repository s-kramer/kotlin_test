
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
 * - empty string
 * - VX, XXXXL, LXXX, LXXXX, IIX
 * - IX, XXL
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

    @Test
    fun numericalValueOfSingleLetterDIsRecognized() {
        assertThat(romanToDecimal(ROMAN_FIVE_HUNDRED), Is.`is`(500))
    }

    @Test
    fun numericalValueOfSingleLetterMIsRecognized() {
        assertThat(romanToDecimal(ROMAN_THOUSAND), Is.`is`(1000))
    }

    private fun concatLetters(vararg value: Char) = StringBuilder().append(value).toString()

    @Test
    fun numericalValueOfTwoSingleLettersGivesTheirSum() {
        val romanNumber = concatLetters(ROMAN_ONE, ROMAN_ONE)
        assertThat(romanToDecimal(romanNumber), Is.`is`(2))
    }

    @Test
    fun simpleConcatenationsConfirmationTest() {
        assertThat(romanToDecimal(concatLetters(ROMAN_FIVE, ROMAN_FIVE)), Is.`is`(10))
        assertThat(romanToDecimal(concatLetters(ROMAN_TEN, ROMAN_TEN)), Is.`is`(20))
        assertThat(romanToDecimal(concatLetters(ROMAN_FIFTY, ROMAN_FIFTY)), Is.`is`(100))
        assertThat(romanToDecimal(concatLetters(ROMAN_HUNDRED, ROMAN_HUNDRED)), Is.`is`(200))
        assertThat(romanToDecimal(concatLetters(ROMAN_FIVE_HUNDRED, ROMAN_FIVE_HUNDRED)), Is.`is`(1000))
        assertThat(romanToDecimal(concatLetters(ROMAN_THOUSAND, ROMAN_THOUSAND)), Is.`is`(2000))
    }

    @Test
    fun concatenationOfThreeRomanOnesIsAccepted() {
        assertThat(romanToDecimal(concatLetters(ROMAN_ONE, ROMAN_ONE, ROMAN_ONE)), Is.`is`(3))
    }

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfFourOnesIsRejected() {
        romanToDecimal(concatLetters(ROMAN_ONE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE))
    }
}

