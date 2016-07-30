package roman

val ROMAN_ONE = 'I'

val ROMAN_FIVE = 'V'

val ROMAN_TEN = 'X'

val ROMAN_FIFTY = 'L'

val ROMAN_HUNDRED = 'C'

val ROMAN_FIVE_HUNDRED = 'D'

val ROMAN_THOUSAND = 'M'

fun romanToDecimal(romanLetter: Char): Int = convertCharToNumber(romanLetter)

private val MAX_REPETITIONS = 3

fun romanToDecimal(romanString: String): Int {
    checkArguments(romanString)

    return parseToIntList(romanString.toUpperCase()).sum()
}

fun  checkArguments(romanString: String) {
    checkLength(romanString)
    checkLetterRepetitions(romanString)
}

fun checkLength(romanString: String) {
    if (romanString.length == 0) throw IllegalArgumentException("Roman number string cannot be empty")
}

private fun checkLetterRepetitions(romanString: String) {
    var repetitionCount = 1
    var lastNumber = romanString[0]
    val iterator = romanString.substring(1).iterator()
    while (iterator.hasNext()) {
        val number = iterator.next()
        if (lastNumber == number) {
            if (++repetitionCount > MAX_REPETITIONS) {
                throw IllegalArgumentException("$MAX_REPETITIONS or more identical characters are not allowed")
            }
        } else {
            lastNumber = number
            repetitionCount = 0
        }
    }
}

private fun parseToIntList(romanString: String): List<Int> = romanString.map(::convertCharToNumber)

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