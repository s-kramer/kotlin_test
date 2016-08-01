package roman

import algorithm.groupAdjacentBy

enum class RomanNumber(val number: Int, val char: Char, val max_repetition: Int = 3) {

    ROMAN_ONE(1, 'I'),

    ROMAN_FIVE(5, 'V', max_repetition = 1),

    ROMAN_TEN(10, 'X'),

    ROMAN_FIFTY(50, 'L', max_repetition = 1),

    ROMAN_HUNDRED(100, 'C'),

    ROMAN_FIVE_HUNDRED(500, 'D', max_repetition = 1),

    ROMAN_THOUSAND(1000, 'M');
}

fun romanToDecimal(romanNumber: RomanNumber, vararg romanNumbers: RomanNumber): Int {
    return romanToDecimal(listOf(romanNumber, *romanNumbers))
}

fun romanToDecimal(romanNumberString: String): Int {
    val romanNumberList = romanNumberString
            .map { letter -> RomanNumber.values().find { enum_value -> enum_value.char == letter } }
            .toList()

    return romanToDecimal(romanNumberList.requireNoNulls())
}

fun romanToDecimal(romanNumberList: List<RomanNumber>): Int {
    checkPreconditions(romanNumberList)

    val rawConvertedValues = convertRomanNumbersToDecimal(romanNumberList)
    return parseRawConvertedValues(rawConvertedValues)
}

private fun parseRawConvertedValues(rawConvertedValues: List<Int>): Int {
    val increasingValuesGroups = splitIntoGroupsOfIncreasingValues(rawConvertedValues)
    val increasingValuesGroupsFinalValues = calculateFinalValuesOfIncreasingValuesGroups(increasingValuesGroups)
    return increasingValuesGroupsFinalValues.sum()
}

private fun calculateFinalValuesOfIncreasingValuesGroups(increasingValuesGroups: List<List<Int>>): List<Int> {
    return increasingValuesGroups.map { valuesList -> return@map calculateGroupFinalValue(valuesList) }
}

private fun calculateGroupFinalValue(valuesList: List<Int>): Int {
    if (containsOnlyOneDistinctElement(valuesList)) {
        return valuesList.sum()
    }

    return calculateFinalValueForSubtractiveGroup(valuesList)
}

private fun calculateFinalValueForSubtractiveGroup(valuesList: List<Int>) = 2 * valuesList.last() - valuesList.sum()

private fun containsOnlyOneDistinctElement(valuesList: List<Int>) = valuesList.first() == valuesList.last()

private fun splitIntoGroupsOfIncreasingValues(
        rawConvertedValues: List<Int>) = rawConvertedValues.groupAdjacentBy { lhs, rhs -> lhs.compareTo(rhs) <= 0 }

private fun checkPreconditions(romanNumbers: List<RomanNumber>) {
    checkLetterRepetitions(romanNumbers)
}

private fun checkLetterRepetitions(romanNumbers: List<RomanNumber>) {
    val groupedRomanNumbers = getGroupsOfEqualNumbers(romanNumbers)
    val offendingLettersGroups = getGroupsWithTooManyElements(groupedRomanNumbers)

    if (!offendingLettersGroups.isEmpty()) {
        val sb = StringBuilder()
        offendingLettersGroups.forEach { list ->
            sb.append(
                    "Concatenation of ${list.size} characters ${list[0].name} violates maximum repetition count ${list[0].max_repetition}")
        }

        throw IllegalArgumentException(sb.toString())

    }
}

private fun getGroupsWithTooManyElements(
        groupedRomanNumbers: List<List<RomanNumber>>) = groupedRomanNumbers.filter { list -> list.size > list[0].max_repetition }

private fun getGroupsOfEqualNumbers(
        romanNumbers: List<RomanNumber>) = romanNumbers.groupAdjacentBy { lhs, rhs -> lhs.number == rhs.number }

private fun convertRomanNumbersToDecimal(romanNumber: List<RomanNumber>): List<Int> = romanNumber.map(
        ::convertSingleRomanNumberToDecimal)

private fun convertSingleRomanNumberToDecimal(romanNumber: RomanNumber): Int = romanNumber.number