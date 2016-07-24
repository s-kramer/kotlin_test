package game

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by skramer on 7/23/16.
 *
 * <s>-place first peg</s>
 * <s>-place second peg</s>
 * <s>-place peg on an already taken field</s>
 * <s>-detect row match</s>
 * <s>-detect column match</s>
 * <s>-detect diagonal match</s>
 * -declare winner by row match
 * -declare winner by column match
 * -declare winner by diagonal match
 * -place peg after game has been already won
 *
 */

class TicTacToeTest {
    lateinit var board: TicTacToeBoard

    @Before
    fun setup() {
        board = TicTacToeBoard()
    }

    @Test
    fun placeFirstPeg() {
        assertThat(board.placePeg(0, 0), `is`(TicTacToeBoard.Peg.FIRST))
    }

    @Test
    fun placeSecondPeg() {
        board.placePeg(0, 0)

        assertThat(board.placePeg(1, 0), `is`(TicTacToeBoard.Peg.SECOND))
    }

    @Test
    fun pegCannotBePlacedOnAnAlreadyOccupiedField() {
        board.placePeg(0, 0)

        assertThat(board.placePeg(0, 0), `is`(TicTacToeBoard.Peg.FIRST))
    }

    @Test
    fun detectRowMatch() {
        performRowMatchByFirstPlayer()

        assertThat(board.checkRowMatch(), `is`(TicTacToeBoard.Peg.FIRST))
    }

    private fun performRowMatchByFirstPlayer() {
        board.placePeg(0, 0)
        board.placePeg(1, 0)
        board.placePeg(0, 1)
        board.placePeg(2, 0)
        board.placePeg(0, 2)
    }

    @Test
    fun detectNoRowMatch() {
        board.placePeg(0, 0)
        board.placePeg(1, 0)
        board.placePeg(0, 1)
        board.placePeg(2, 0)

        assertThat(board.checkRowMatch(), `is`(TicTacToeBoard.Peg.NONE))
    }

    @Test
    fun detectColumnMatch() {
        performColumnMatchByFirstPlayer()

        assertThat(board.checkColumnMatch(), `is`(TicTacToeBoard.Peg.FIRST))
    }

    private fun performColumnMatchByFirstPlayer() {
        board.placePeg(0, 0)
        board.placePeg(0, 1)
        board.placePeg(1, 0)
        board.placePeg(0, 2)
        board.placePeg(2, 0)
    }

    @Test
    fun detectNoColumnMatch() {
        board.placePeg(0, 0)
        board.placePeg(0, 1)
        board.placePeg(1, 0)
        board.placePeg(0, 2)

        assertThat(board.checkColumnMatch(), `is`(TicTacToeBoard.Peg.NONE))
    }

    @Test
    fun detectDiagonalMatch() {
        performDiagonalMatchByFirstPlayer()

        assertThat(board.checkDiagonalMatch(), `is`(TicTacToeBoard.Peg.FIRST))
    }

    private fun performDiagonalMatchByFirstPlayer() {
        board.placePeg(0, 0)
        board.placePeg(0, 1)
        board.placePeg(1, 1)
        board.placePeg(0, 2)
        board.placePeg(2, 2)
    }

    @Test
    fun detectNoDiagonalMatch() {
        board.placePeg(0, 0)
        board.placePeg(0, 1)
        board.placePeg(1, 0)
        board.placePeg(0, 2)

        assertThat(board.checkDiagonalMatch(), `is`(TicTacToeBoard.Peg.NONE))
    }

    @Test
    fun declareWinnerAfterColumnMatch() {
        performColumnMatchByFirstPlayer()

        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.FIRST))
    }

    @Test
    fun declareWinnerAfterRowMatch() {
        performRowMatchByFirstPlayer()

        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.FIRST))
    }

    @Test
    fun declareWinnerAfterDiagonalMatch() {
        performDiagonalMatchByFirstPlayer()

        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.FIRST))
    }

    @Test
    fun noWinnerWhenNoMatchWasDetected() {
        board.placePeg(0, 0)
        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.NONE))
    }

    @Test
    fun noWinnerAtTheBeginningOfTheGame() {
        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.NONE))
    }

    @Test
    fun noPegCanBePlacedAfterGameWasAlreadyWon() {
        board.placePeg(0, 0)
        board.placePeg(1, 0)
        board.placePeg(0, 1)
        board.placePeg(2, 0)
        board.placePeg(0, 2)
        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.FIRST))

        assertThat(board.placePeg(2, 2), `is`(TicTacToeBoard.Peg.NONE))
    }

    @Test
    fun columnMatchesOfNoneAreNotMatches() {
        board.placePeg(0, 2)
        board.placePeg(0, 1)
        board.placePeg(1, 2)
        board.placePeg(0, 0)
        board.placePeg(2, 2)

        assertThat(board.winner(), `is`(TicTacToeBoard.Peg.FIRST))
    }

}

