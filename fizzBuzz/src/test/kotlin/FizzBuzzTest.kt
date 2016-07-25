/**
 * Created by skramer on 7/25/16.
 * -int is returned as string
 * -single int dividable by 3 returns Fizz
 * -single ing dividable by 5 returns Buzz
 * -15 returns FizzBuzz
 *
 */

import fizzbuzz.BUZZ
import fizzbuzz.FIZZ
import fizzbuzz.FIZZBUZZ
import fizzbuzz.fizzBuzz
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class FizzBuzzTest {

    @Test
    fun intIsReturnedAsStringIfNotDividable() {
        assertThat(fizzBuzz(1), `is`("1"))
        assertThat(fizzBuzz(2), `is`("2"))
    }

    @Test
    fun intsDividableByThreeAreFizz() {
        assertThat(fizzBuzz(3), `is`(FIZZ))
    }

    @Test
    fun intsDividableByFiveAreBuzz() {
        assertThat(fizzBuzz(5), `is`(BUZZ))
    }

    @Test
    fun intsDividableByThreeAndFiveAreFizzBuzz() {
        assertThat(fizzBuzz(15), `is`(FIZZBUZZ))
    }
}
