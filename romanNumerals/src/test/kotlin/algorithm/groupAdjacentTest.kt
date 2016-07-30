package algorithm

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by skramer on 7/30/16.
 * Tests for groupAdjacent algorithm.
 * - <s>single element</s>
 * - <s>two equal elements</s>
 * - <s>two different elements</s>
 * - only equal elements return list with a single pair
 * - all elements different return list of pairs of all elements each with 1
 * - empty list
 */

class GroupAdjacentTest() {
    @Test
    fun singleElementIsPlacedInSinglePair() {
        assertThat(listOf(1).groupAdjacent(), `is`(listOf(listOf(1))))
    }

    @Test
    fun twoEqualElementsReturnListWithOnlyOnePair() {
        assertThat(listOf(1, 1).groupAdjacent(), `is`(listOf(listOf(1, 1))))
    }

    @Test
    fun multipleEqualElementsReturnListWithOnlyOnePair() {
        val equalElements = (1 .. 100).map { 1 }.toList()
        assertThat(equalElements.groupAdjacent(), `is`(listOf(equalElements)))
    }

    @Test
    fun twoDifferentElementsReturnPairEach() {
        assertThat(listOf(1, 2).groupAdjacent(), `is`(listOf(listOf(1), listOf(2))))
    }
}
