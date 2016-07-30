package roman

import algorithm.groupAdjacentBy

enum class RomanNumber(val number: Int, val max_repetition: Int = 3) {

    ROMAN_ONE(1),

    ROMAN_FIVE(5, max_repetition = 1),

    ROMAN_TEN(10),

    ROMAN_FIFTY(50, max_repetition = 1),

    ROMAN_HUNDRED(100),

    ROMAN_FIVE_HUNDRED(500, max_repetition = 1),

    ROMAN_THOUSAND(1000);
}

fun romanToDecimal(romanNumber: RomanNumber, vararg romanNumbers: RomanNumber): Int {
    val romanNumberArray = arrayOf(romanNumber, *romanNumbers)

    checkPreconditions(romanNumberArray)

    val rawConvertedValues = convertRomanNumbersToDecimal(romanNumberArray)
    return parseRawConvertedValues(rawConvertedValues)
}

private fun parseRawConvertedValues(rawConvertedValues: List<Int>): Int {
    return rawConvertedValues.groupAdjacentBy { lhs, rhs -> lhs.compareTo(rhs) < 0 }
            .map { valuesList -> valuesList.last() - valuesList.subList(0, valuesList.size - 1).sum() }
            .sum()
}

private fun checkPreconditions(romanNumbers: Array<out RomanNumber>) {
    checkLetterRepetitions(romanNumbers)
}

private fun checkLetterRepetitions(romanNumbers: Array<out RomanNumber>) {
    val groupedRomanNumbers = romanNumbers.asList().groupAdjacentBy { lhs, rhs -> lhs.number == rhs.number }
    val offendingLettersLists = groupedRomanNumbers.filter { list -> list.size > list[0].max_repetition }

    for (list in offendingLettersLists) {
        throw IllegalArgumentException(
                "Concatenation of ${list.size} characters ${list[0].name} violates maximum repetition count ${list[0].max_repetition}")
    }
}

private fun convertRomanNumbersToDecimal(romanNumber: Array<out RomanNumber>): List<Int> = romanNumber.map(
        ::convertSingleRomanNumberToDecimal)

private fun convertSingleRomanNumberToDecimal(romanNumber: RomanNumber): Int = romanNumber.number