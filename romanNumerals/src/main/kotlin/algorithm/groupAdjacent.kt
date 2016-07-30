package algorithm

/**
 * Created by skramer on 7/30/16.
 *
 * groupAdjacent algorithm. Uses the provided predicate to group subsequent entries in the collection.
 */

fun <E> List<E>.groupAdjacent(): List<List<E>> {
    var result = arrayListOf<List<E>>()

    var currentPrototypeIndex = 0

    while (currentPrototypeIndex < this.size) {
        val list = subList(currentPrototypeIndex, this.size).takeWhile { it == this[currentPrototypeIndex] }
        result.add(list)
        currentPrototypeIndex += list.size
    }

    return result
}
