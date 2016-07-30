package algorithm

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by skramer on 7/30/16.
 * Tests for groupAdjacentBy algorithm.
 * - <s>single element</s>
 * - <s>two equal elements</s>
 * - <s>two different elements</s>
 * - <s>all elements different return list of lists, each with one element</s>
 * - <s>empty list</s>
 * - singleton list
 * <s>- list with multiple, not subsequent occurrences of an element</s>
 */

class GroupAdjacentTest() {
    @Test
    fun singleElementIsPlacedInSinglePair() {
        assertThat(listOf(1).groupAdjacentBy(Int::equals), `is`(listOf(listOf(1))))
    }

    @Test
    fun twoEqualElementsReturnListWithOnlyOnePair() {
        assertThat(listOf(1, 1).groupAdjacentBy(Int::equals), `is`(listOf(listOf(1, 1))))
    }

    @Test
    fun multipleEqualElementsReturnListWithOnlyOnePair() {
        val equalElements = (1 .. 100).map { 1 }.toList()
        assertThat(equalElements.groupAdjacentBy(Int::equals), `is`(listOf(equalElements)))
    }

    @Test
    fun twoDifferentElementsReturnPairEach() {
        assertThat(listOf(1, 2).groupAdjacentBy(Int::equals), `is`(listOf(listOf(1), listOf(2))))
    }

    @Test
    fun allElementsDifferentReturnListForEachEntry() {
        val inputArray = (1..100).toList()
        val expectedOutput = (1..100).map { listOf(it) }
        assertThat(inputArray.groupAdjacentBy(Int::equals), `is`(expectedOutput))
    }

    @Test
    fun multipleNotSubSequentOccurrencesOfDifferentElements() {
        val inputArray = arrayListOf<Int>()
        for (i in 1..10) {
            inputArray.addAll(1..10)
        }
        assertThat(inputArray.size, `is`(100))

        val expectedOutput = inputArray.map { listOf(it) }

        assertThat(inputArray.groupAdjacentBy(Int::equals), `is`(expectedOutput.toList()))
    }

    @Test
    fun multipleNotSubSequentOccurrencesOfDifferentElementsWithRepetitions() {
        val inputArray = arrayListOf<Int>()
        (1..10).forEach { inputArray.addAll(listOf(it, it, it)) }

        val expected = (1..10).map { listOf(it, it, it) }.toList()
        assertThat(inputArray.groupAdjacentBy(Int::equals), `is`(expected.toList()))
    }

    @Test
    fun emptyList() {
        val expected: List<List<Int>> = listOf()
        val actual: List<List<Int>> = listOf<Int>().groupAdjacentBy(Int::equals)
        assertThat(actual, `is`(expected))
    }

    val lessThanPredicate: (Int, Int) -> Boolean = { left, right -> left.compareTo(right) < 0 }

    @Test
    fun singletonList() {
        assertThat(listOf(1).groupAdjacentBy ( Int::equals ), `is`(listOf(listOf(1))))
        assertThat(listOf(1).groupAdjacentBy ( lessThanPredicate ), `is`(listOf(listOf(1))))
    }

    @Test
    fun groupElementsUntilIncreasingTrendIsOver() {
        val input = listOf(1, 2, 3, 0, 5, 6, 0, 8, 9)
        assertThat(input.groupAdjacentBy(lessThanPredicate), `is`(listOf(listOf(1, 2, 3), listOf(0, 5, 6), listOf(0, 8, 9))))
    }
}
