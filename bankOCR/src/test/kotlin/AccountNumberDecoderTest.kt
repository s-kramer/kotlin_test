import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by skramer on 7/24/16.
 * -detect numbers 0-9
 * -throw if unidentified number
 * -entries have less\more than 4 lines
 * -line has less\more than 27 characters
 * -the fourth line is not empty
 * -fourth line contains only spaces
 * -character other than pipe or underscore was found
 *
 * -calculate checksum for all the identical digits
 * -checksum for incorrect digits
 *
 * -
 */

class AccountNumberDecoderTest {
    @Test
    fun sanityTest() {
        assertEquals(true, true)
    }
}
