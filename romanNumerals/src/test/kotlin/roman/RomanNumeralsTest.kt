package roman

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import roman.RomanNumber.*
import kotlin.test.assertTrue

/**
 * Created by skramer on 7/25/16.
 * - <s>single values, ROMAN_ONE, V, X, L, C, D, M</s>
 * - <s>concatenated single values II, III, XX, DD</s>
 * - <s>illegal concatenations IIII, XXXX</s>
 * - <s>mixed simple additive concatenations XI, VI, LX</s>
 * - <s>MMMDCCCLXXXVIII == 3888</s>
 * - <s>mixed simple subtractive concatenations IX, XL</s>
 * - illegal subtractive concatenations e.g. IM, XXXL, VX, XXXXL, LXXX, LXXXX, IIX
 * - <s>acceptance test: 1999</s>
 * - <s>boundary values: 1, 3000, 1000, 666, 999, 888<s>
 * - exception message text
 * - <s>convert from string? (case sensitivity, empty string, unknown letter)</s>
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
    fun concatenationOfFourTensIsRejected() {
        romanToDecimal(ROMAN_TEN, ROMAN_TEN, ROMAN_TEN, ROMAN_TEN)
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

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfFourHundredsIsRejected() {
        romanToDecimal(ROMAN_HUNDRED, ROMAN_HUNDRED, ROMAN_HUNDRED, ROMAN_HUNDRED)
    }

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfTwoFiveHundredsIsRejected() {
        romanToDecimal(ROMAN_FIVE_HUNDRED, ROMAN_FIVE_HUNDRED)
    }

    @Test
    fun twoRomanThousandsCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_THOUSAND, ROMAN_THOUSAND), `is`(2000))
    }

    @Test
    fun threeRomanThousandsCanBeConcatenated() {
        assertThat(romanToDecimal(ROMAN_THOUSAND, ROMAN_THOUSAND, ROMAN_THOUSAND), `is`(3000))
    }

    @Test(expected = IllegalArgumentException::class)
    fun concatenationOfFourThousandsIsRejected() {
        romanToDecimal(ROMAN_THOUSAND, ROMAN_THOUSAND, ROMAN_THOUSAND, ROMAN_THOUSAND)
    }

    @Test
    fun additiveConcatenationOfFiveAndOne() {
        assertThat(romanToDecimal(ROMAN_FIVE, ROMAN_ONE), `is`(6))
        assertThat(romanToDecimal(ROMAN_FIVE, ROMAN_ONE, ROMAN_ONE), `is`(7))
        assertThat(romanToDecimal(ROMAN_FIVE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE), `is`(8))
    }

    @Test(expected = IllegalArgumentException::class)
    fun additiveConcatenationOfFiveAndFourOnesIsRejected() {
        romanToDecimal(ROMAN_FIVE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE)
    }

    @Test
    fun additiveConcatenationOfTenAndFive() {
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_FIVE), `is`(15))
    }

    @Test
    fun additiveConcatenationOfTenFiveAndThreeOnes() {
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_FIVE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE), `is`(18))
    }

    @Test
    fun maximalAdditiveConcatenation() {
        assertThat(romanToDecimal(ROMAN_THOUSAND, ROMAN_THOUSAND, ROMAN_THOUSAND, ROMAN_FIVE_HUNDRED,
                                  ROMAN_HUNDRED, ROMAN_HUNDRED, ROMAN_HUNDRED,
                                  ROMAN_FIFTY, ROMAN_TEN, ROMAN_TEN, ROMAN_TEN,
                                  ROMAN_FIVE, ROMAN_ONE, ROMAN_ONE, ROMAN_ONE), `is`(3888))
    }

    @Test
    fun subtractiveConcatenationWithSingleValue() {
        assertThat(romanToDecimal(ROMAN_ONE, ROMAN_FIVE), `is`(4))
        assertThat(romanToDecimal(ROMAN_ONE, ROMAN_TEN), `is`(9))
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_FIFTY), `is`(40))
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_HUNDRED), `is`(90))
        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_FIVE_HUNDRED), `is`(400))
        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_THOUSAND), `is`(900))
    }

    @Test
    fun subtractiveConcatenationWithMultipleValues() {
        assertThat(romanToDecimal(ROMAN_TEN, ROMAN_TEN, ROMAN_FIFTY), `is`(30))
        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_HUNDRED, ROMAN_FIVE_HUNDRED), `is`(300))
        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_HUNDRED, ROMAN_THOUSAND), `is`(800))
    }

    @Test
    fun acceptanceTest() {
        assertThat(romanToDecimal(ROMAN_THOUSAND, ROMAN_HUNDRED, ROMAN_THOUSAND,
                                  ROMAN_TEN, ROMAN_HUNDRED, ROMAN_ONE, ROMAN_TEN), `is`(1999))
    }

    @Test
    fun remainingBoundaryValues() {
        assertThat(romanToDecimal(ROMAN_FIVE_HUNDRED, ROMAN_HUNDRED, ROMAN_FIFTY, ROMAN_TEN, ROMAN_FIVE, ROMAN_ONE),
                   `is`(666))

        assertThat(romanToDecimal(ROMAN_HUNDRED, ROMAN_THOUSAND,
                                  ROMAN_TEN, ROMAN_HUNDRED, ROMAN_ONE, ROMAN_TEN), `is`(999))
    }

    @Test
    fun convertFromString() {
        assertThat(romanToDecimal("MMMDCCCLXXXVIII"), `is`(3888))
        assertThat(romanToDecimal("MMM"), `is`(3000))
        assertThat(romanToDecimal("IX"), `is`(9))
        assertThat(romanToDecimal("CCXC"), `is`(290))
    }

    @Test
    fun emptyString() {
        assertThat(romanToDecimal(""), `is`(0))
    }

    @Test
    fun caseSensitivityIsIgnored() {
        assertThat(romanToDecimal("mmmdccclxxxviii"), `is`(3888))
    }

    @Test(expected = IllegalArgumentException::class)
    fun unknownLetterIsRejected() {
        romanToDecimal("Z")
    }

    @Test
    fun illegalSubtractiveConcatenationsAreRejected() {
        assertTrue(throwsIllegalArgumentException("IM"))
        assertTrue(throwsIllegalArgumentException("LM"))
        assertTrue(throwsIllegalArgumentException("XD"))
        assertTrue(throwsIllegalArgumentException("ID"))
        assertTrue(throwsIllegalArgumentException("LM"))
    }

    private fun throwsIllegalArgumentException(romanString: String): Boolean {
        try {
            romanToDecimal(romanString)
            return false
        } catch (_ : IllegalArgumentException) {
            return true
        }
    }

    @Test
    fun specificSubtractiveConcatenationsAreHandled() {
        assertTrue(throwsIllegalArgumentException("VX"))
    }
}
