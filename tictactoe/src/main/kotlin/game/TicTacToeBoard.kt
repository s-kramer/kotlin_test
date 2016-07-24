package game

class TicTacToeBoard {
    val SIZE = 3

    enum class Peg {NONE, FIRST, SECOND }

    private var currentPeg: Peg = Peg.FIRST

    private var fields: Array<Array<Peg>> = Array(3, { Array(3, { i -> Peg.NONE }) })

    fun placePeg(x: Int, y: Int): Peg {
        val previousFieldPeg = fields[x][y]

        if (wasGameAlreadyFinished()) {
            return Peg.NONE
        }

        if (previousFieldPeg == Peg.NONE) {
            return setField(x, y)
        }
        return previousFieldPeg
    }

    private fun setField(x: Int, y: Int): Peg {
        fields[x][y] = currentPeg
        toggleCurrentPeg()
        return fields[x][y]
    }

    private fun wasGameAlreadyFinished() = getMatch() != Peg.NONE

    private fun toggleCurrentPeg() {
        currentPeg = if (currentPeg == Peg.FIRST) Peg.SECOND else Peg.FIRST
    }

    fun checkRowMatch(): Peg {
        for (i in 0 until SIZE) {
            if (fields[i][0] == fields[i][1] && fields[i][1] == fields[i][2] && fields[i][0] != Peg.NONE) {
                return fields[i][0]
            }
        }

        return Peg.NONE
    }

    fun checkColumnMatch(): Peg {
        for (i in 0 until SIZE) {
            if (fields[0][i] == fields[1][i] && fields[1][i] == fields[2][i] && fields[0][i] != Peg.NONE) {
                return fields[0][i]
            }
        }

        return Peg.NONE
    }

    fun checkDiagonalMatch(): Peg {
        val leftDownDiagonal = checkLeftDownDiagonal()
        if (leftDownDiagonal != Peg.NONE) return leftDownDiagonal
        return checkLeftUpDiagonal()
    }

    private fun checkLeftUpDiagonal() = if (fields[0][2] == fields[1][1] && fields[1][1] == fields[2][0] && fields[0][2] != Peg.NONE) fields[0][2] else Peg.NONE

    private fun checkLeftDownDiagonal() = if (fields[0][0] == fields[1][1] && fields[1][1] == fields[2][2] && fields[0][2] != Peg.NONE) fields[0][0] else Peg.NONE

    fun winner(): Peg = getMatch()

    private fun getMatch(): Peg {
        val columnMatch = checkColumnMatch()
        if (columnMatch != Peg.NONE) {
            return columnMatch
        } else {
            val rowMatch = checkRowMatch()
            if (rowMatch != Peg.NONE) {
                return rowMatch
            } else {
                return checkDiagonalMatch()
            }
        }
    }

}