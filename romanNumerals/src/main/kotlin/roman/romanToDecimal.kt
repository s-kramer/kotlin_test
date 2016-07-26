package roman

fun romanToDecimal(romanString: String): Int {
    return when(romanString) {
        "I" -> 1
        else -> {
            throw IllegalArgumentException("Unknown roman character ${romanString}")
        }
    }
}