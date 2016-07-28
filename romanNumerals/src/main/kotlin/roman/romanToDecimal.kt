package roman

val ROMAN_ONE = 'I'

val ROMAN_FIVE = 'V'

val ROMAN_TEN = 'X'

val ROMAN_FIFTY = 'L'

val ROMAN_HUNDRED = 'C'

val ROMAN_FIVE_HUNDRED = 'D'

val ROMAN_THOUSAND = 'M'

fun romanToDecimal(romanLetter: Char): Int = convertCharToNumber(romanLetter)

fun romanToDecimal(romanString: String): Int {
    return romanString.map ( ::convertCharToNumber ).sum()
}

private fun convertCharToNumber(romanString: Char): Int {
    return when (romanString) {
        ROMAN_ONE -> 1
        ROMAN_FIVE -> 5
        ROMAN_TEN -> 10
        ROMAN_FIFTY -> 50
        ROMAN_HUNDRED -> 100
        ROMAN_FIVE_HUNDRED -> 500
        ROMAN_THOUSAND -> 1000
        else -> {
            throw IllegalArgumentException("Unknown roman character ${romanString}")
        }
    }
}