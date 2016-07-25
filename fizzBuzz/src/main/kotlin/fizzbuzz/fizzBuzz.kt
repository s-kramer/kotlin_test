package fizzbuzz

val FIZZ: String = "Fizz"
val BUZZ: String = "Buzz"

fun fizzBuzz(i: Int): String {
    if (i % 3 == 0) {
        return FIZZ
    }
    if (i % 5 == 0) {
        return BUZZ
    }
    return i.toString()
}