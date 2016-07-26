package roman

fun romanToDecimal(romanString: String): Int {
    return when(romanString) {
        "I" -> 1
        "V" -> 5
        "X" -> 10
        else -> {
            throw IllegalArgumentException("Unknown roman character ${romanString}")
        }
    }
}