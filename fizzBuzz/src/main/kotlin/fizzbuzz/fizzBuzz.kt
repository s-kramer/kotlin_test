package fizzbuzz

val FIZZ: String = "Fizz"
val BUZZ: String = "Buzz"
val FIZZBUZZ: String = "FizzBuzz"

fun fizzBuzz(i: Int): String {
    val builder = StringBuilder()
    if (i % 3 == 0) {
        builder.append(FIZZ)
    }

    if (i % 5 == 0) {
        builder.append(BUZZ)
    }

    return if (!builder.isEmpty()) builder.toString() else i.toString()
}