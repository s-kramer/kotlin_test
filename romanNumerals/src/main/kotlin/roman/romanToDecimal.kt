package roman

enum class RomanNumber(val representation: Char, val number: Int, val max_repetition: Int = 3) {

    ROMAN_ONE('I', 1),

    ROMAN_FIVE('V', 5, max_repetition = 1),

    ROMAN_TEN('X', 10),

    ROMAN_FIFTY('L', 50),

    ROMAN_HUNDRED('C', 100),

    ROMAN_FIVE_HUNDRED('D', 500),

    ROMAN_THOUSAND('M', 1000);
}

fun romanToDecimal(romanLetter: RomanNumber): Int = convertSingleRomanNumberToDecimal(romanLetter)

fun romanToDecimal(romanNumber: RomanNumber, vararg romanNumbers: RomanNumber): Int {
    val romanNumberArray = arrayOf(romanNumber, *romanNumbers)

    checkArguments(romanNumberArray)

    return convertRomanNumbersToDecimal(romanNumberArray).sum()
}

fun checkArguments(romanNumbers: Array<out RomanNumber>) {
    checkLength(romanNumbers)
    checkLetterRepetitions(romanNumbers)
}

fun checkLength(romanNumbers: Array<out RomanNumber>) {
    if (romanNumbers.size == 0) throw IllegalArgumentException("Roman number string cannot be empty")
}

private fun checkLetterRepetitions(romanNumbers: Array<out RomanNumber>) {
    var repetitionCount = 1
    var lastNumber = romanNumbers[0]
    val iterator = romanNumbers.asList().listIterator(1)
    while (iterator.hasNext()) {
        val number = iterator.next()
        if (lastNumber == number) {
            if (++repetitionCount > number.max_repetition) {
                throw IllegalArgumentException("Concatenation of ${number.max_repetition} characters ${number.representation} is not allowed")
            }
        } else {
            lastNumber = number
            repetitionCount = 0
        }
    }
}

private fun convertRomanNumbersToDecimal(romanNumber: Array<out RomanNumber>): List<Int> = romanNumber.map(::convertSingleRomanNumberToDecimal)

private fun convertSingleRomanNumberToDecimal(romanNumber: RomanNumber): Int {
    return romanNumber.number
}