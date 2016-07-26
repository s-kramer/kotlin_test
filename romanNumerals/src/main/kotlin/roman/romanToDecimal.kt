package roman

val ROMAN_ONE = "I"

val ROMAN_FIVE: String = "V"

val ROMAN_TEN: String = "X"

val ROMAN_FIFTY: String = "L"

val ROMAN_HUNDRED: String = "C"

fun romanToDecimal(romanString: String): Int {
    return when(romanString) {
        ROMAN_ONE -> 1
        ROMAN_FIVE -> 5
        ROMAN_TEN -> 10
        ROMAN_FIFTY -> 50
        ROMAN_HUNDRED -> 100
        else -> {
            throw IllegalArgumentException("Unknown roman character ${romanString}")
        }
    }
}