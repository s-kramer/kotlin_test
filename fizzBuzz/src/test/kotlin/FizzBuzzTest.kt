import fizzbuzz.fizzBuzz
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by skramer on 7/25/16.
 * -int is returned as string
 * -single int dividable by 3 returns Fizz
 * -single ing dividable by 5 returns Buzz
 * -15 returns FizzBuzz
 *
 */

class FizzBuzzTest {

    @Test
    fun intIsReturnedAsStringIfNotDividable() {
        assertThat(fizzBuzz(1), `is`("1"))
    }
}
