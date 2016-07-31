package algorithm

import java.util.*

/**
 * Created by skramer on 7/30/16.
 *
 * groupAdjacentBy algorithm. Uses the provided predicate to group subsequent entries in the collection.
 */

fun <E> List<E>.groupAdjacentBy(binaryPredicate: (E, E) -> Boolean): List<List<E>> {
    val result = arrayListOf<List<E>>()

    var currentElementIndex = 0

    while (currentElementIndex < this.size) {
        var currentElement = this[currentElementIndex]

        val list = subList(currentElementIndex + 1, this.size).takeWhile {
            val predicateResult = binaryPredicate(currentElement, it)
            currentElement = it
            return@takeWhile predicateResult
        }

        result.add(getPartialResultAsList(currentElementIndex, list))

        currentElementIndex += 1 + list.size
    }

    return result
}

private fun <E> List<E>.getPartialResultAsList(currentElementIndex: Int, list: List<E>): ArrayList<E> {
    val partialResult = arrayListOf(this[currentElementIndex])
    partialResult.addAll(list)
    return partialResult
}
