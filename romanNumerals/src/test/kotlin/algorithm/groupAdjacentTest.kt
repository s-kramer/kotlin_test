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
 * - <s>all elements different return list of lists, each with one element</s>
 * - empty list
 * - list with multiple, not subsequent occurrences of an element
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

    @Test
    fun allElementsDifferentReturnListForEachEntry() {
        val inputArray = (1..100).toList()
        val expectedOutput = (1..100).map { listOf(it) }
        assertThat(inputArray.groupAdjacent(), `is`(expectedOutput))
    }

    @Test
    fun multipleNotSubSequentOccurrencesOfDifferentElements() {
        val inputArray = arrayListOf<Int>()
        for (i in 1..10) {
            inputArray.addAll(1..10)
        }
        assertThat(inputArray.size, `is`(100))

        val expectedOutput = inputArray.map { listOf(it) }

        assertThat(inputArray.groupAdjacent(), `is`(expectedOutput.toList()))
    }

    @Test
    fun multipleNotSubSequentOccurrencesOfDifferentElementsWithRepetitions() {
        val inputArray = arrayListOf<Int>()
        (1..10).forEach { inputArray.addAll(listOf(it, it, it)) }

        val expected = (1..10).map { listOf(it, it, it) }.toList()
        assertThat(inputArray.groupAdjacent(), `is`(expected.toList()))
    }

}
