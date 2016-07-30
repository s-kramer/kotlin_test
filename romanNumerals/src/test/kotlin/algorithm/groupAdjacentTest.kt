package algorithm

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by skramer on 7/30/16.
 * Tests for groupAdjacent algorithm.
 */

class GroupAdjacentTest() {
    @Test
    fun singleElementIsPlacedInSinglePair() {

        assertThat(listOf(1).groupAdjacent(), `is`(listOf(1 to 1)))
    }
}
