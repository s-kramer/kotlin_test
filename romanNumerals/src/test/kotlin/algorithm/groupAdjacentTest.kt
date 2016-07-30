package algorithm

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by skramer on 7/30/16.
 * Tests for groupAdjacent algorithm.
 * - single element
 * - two equal elements
 * - two different elements
 * - only equal elements return list with a single pair
 * - all elements different return list of pairs of all elements each with 1
 * - empty list
 */

class GroupAdjacentTest() {
    @Test
    fun singleElementIsPlacedInSinglePair() {
        assertThat(listOf(1).groupAdjacent(), `is`(listOf(1 to 1)))
    }

    @Test
    fun twoEqualElementsReturnListWithOnlyOnePair() {
        assertThat(listOf(1, 1).groupAdjacent(), `is`(listOf(1 to 2)))
    }
}
