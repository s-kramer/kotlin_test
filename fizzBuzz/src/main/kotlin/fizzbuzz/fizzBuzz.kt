package fizzbuzz

val FIZZ: String = "Fizz"

fun fizzBuzz(i: Int): String {
    if (i % 3 == 0) {
        return FIZZ
    }
    return i.toString()
}