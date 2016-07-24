package game.UI
import game.TicTacToeBoard
import java.awt.GridLayout
import javax.swing.JFrame
import javax.swing.JOptionPane

/**
 * Created by skramer on 7/23/16.
 * GUI for tic-tac-toe game
 */

class TicTacToeGui : JFrame() {
    lateinit private var ticTacToeBoard: TicTacToeBoard

    override fun frameInit() {
        super.frameInit()

        ticTacToeBoard = TicTacToeBoard()
        setSize(150, 150)
        layout = GridLayout(3, 3)
        createButtons()
    }

    private fun createButtons() {
        for (i in 0 until ticTacToeBoard.SIZE) {
            for (j in 0 until ticTacToeBoard.SIZE) {
                createButton(i, j)
            }
        }
    }

    private fun createButton(i: Int, j: Int) {
        val button = TicTacToeButton(i, j)
        button.setSize(50, 50)
        button.isVisible = true
        button.addActionListener { e ->
            val source: TicTacToeButton = e.source as TicTacToeButton

            updateButtonText(button, source.row, source.col)
            checkWinner()
        }

        add(button)
    }

    private fun checkWinner() {
        val winner = ticTacToeBoard.winner()
        if (winner != TicTacToeBoard.Peg.NONE) {
            JOptionPane.showMessageDialog(this, "Game winner: ${winner}")
        }
    }

    private fun updateButtonText(button: TicTacToeButton, row: Int, col: Int) {
        val peg = ticTacToeBoard.placePeg(row, col)
        if (peg == TicTacToeBoard.Peg.FIRST) button.text = "X"
        else if (peg == TicTacToeBoard.Peg.SECOND) button.text = "O"
    }

}

fun main(args: Array<String>) {
    val ticTacToeGui = TicTacToeGui()
    ticTacToeGui.isVisible = true
}
