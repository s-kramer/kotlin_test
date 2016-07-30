
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import roman.RomanNumber.*
import roman.romanToDecimal

/**
 * Created by skramer on 7/25/16.
 * <s>- single values, ROMAN_ONE, V, X, L, C, D, M</s>
 * - concatenated single values II, III, XX, DD
 * - illegal concatenations IIII, XXXX,
 * - mixed simple additive concatenations XI, VI, LX
 * - mixed simple subtractive concatenations IX, XL
 * - illegal subtractive concatenations e.g. IM, XXXL
 * - acceptance test: 1999
 * - boundary values: 1, 3000, 1000, 666, 999, 888
 * <s>- case sensitivity</s>
 * <s>- empty string</s>
 * - VX, XXXXL, LXXX, LXXXX, IIX
 * - IX, XXL
 */

class RomanNumeralsTest {

    @Test
    fun numericalValueOfSingleLetterIIsRecognized() {
        assertThat(romanToDecimal(ROMAN_ONE), `is`(1))
    }

    @Test
    fun numericalValueOfSingleLetterVIsRecognized() {
        assertThat(romanToDecimal(ROMAN_FIVE), `is`(5))
    }

    @Test
    fun numericalValueOfSingleLetterXIsRecognized() {
        assertThat(romanToDecimal(ROMAN_TEN), `is`(10))
    }

    @Test
    fun numericalValueOfSingleLetterLIsRecognized() {
        assertThat(romanToDecimal(ROMAN_FIFTY), `is`(50))
    }

    @Test
    fun numericalValueOfSingleLetterCIsRecognized() {
        assertThat(romanToDecimal(ROMAN_HUNDRED), `is`(100))
    }

    @Test
    fun numericalValueOfSingleLetterDIsRecognized() {
        assertThat(romanToDecimal(ROMAN_FIVE_HUNDRED), `is`(500))
    }

    @Test
    fun numericalValueOfSingleLetterMIsRecognized() {
        assertThat(romanToDecimal(ROMAN_THOUSAND), `is`(1000))
    }

    @Test
    fun twoRomanOnesCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_ONE, ROMAN_ONE), `is`(2))
    }

    @Test
    fun threeRomanOnesCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_ONE, ROMAN_ONE, ROMAN_ONE), `is`(3))
    }

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfFourRomanOnesIsRejected() {
        romanToDecimal(ROMAN_ONE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE)
    }

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfTwoFivesIsRejected() {
        romanToDecimal(ROMAN_FIVE, ROMAN_FIVE)
    }

    @Test
    fun twoRomanTensCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_TEN), `is`(20))
    }

    @Test
    fun threeRomanTensCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_TEN, ROMAN_TEN), `is`(30))
    }

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfTwoFiftiesIsRejected() {
        romanToDecimal(ROMAN_FIFTY, ROMAN_FIFTY)
    }

    @Test
    fun twoRomanHundredsCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_HUNDRED), `is`(200))
    }

    @Test
    fun threeRomanHundredsCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_HUNDRED, ROMAN_HUNDRED), `is`(300))
    }

    @Test
    fun twoRomanFiveHundredsCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_FIVE_HUNDRED, ROMAN_FIVE_HUNDRED), `is`(1000))
    }

    @Test
    fun twoRomanThousandsCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_THOUSAND, ROMAN_THOUSAND), `is`(2000))
    }
}

