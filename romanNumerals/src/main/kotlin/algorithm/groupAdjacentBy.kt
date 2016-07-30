package algorithm

/**
 * Created by skramer on 7/30/16.
 *
 * groupAdjacentBy algorithm. Uses the provided predicate to group subsequent entries in the collection.
 */

fun <E> List<E>.groupAdjacentBy(binPredicate: (E, E) -> Boolean): List<List<E>> {
    val result = arrayListOf<List<E>>()

    var currentPrototypeIndex = 0

    while (currentPrototypeIndex < this.size) {
        val list = subList(currentPrototypeIndex, this.size).takeWhile { binPredicate(it, this[currentPrototypeIndex]) }
        result.add(list)
        currentPrototypeIndex += list.size
    }

    return result
}
