/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

import data.Board;
import data.Player;
import ui.UI;

public class GameEngine {

    private static Player player1;
    private static Player player2;
    private static Board board;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {

        player1 = new Player('X', true);
        player2 = new Player('O', false);
        board = new Board();

        UI.printWelcome(player1, player2);

        play();
    }

    private static void play() {

        Player current = null;
        boolean isTie = false;
        boolean isWinner = false;

        do {
            current = getCurrentPlayer(current);
            board = TurnController.playTurn(board, current);

            if (MovementHandler.isWinningMovement(board, current)) {
                UI.printWinner(current);
                isWinner = true;
            } else {
                if (!TurnController.existFreeSquares(board, player1, player2)) {
                    UI.printTie();
                    isTie = true;
                }
            }

        } while (!isWinner && !isTie);

    }

    private static Player getCurrentPlayer(Player current) {

        if (current == null) {
            return player1;
        } else {
            if (current == player1) {
                return player2;
            } else {
                return player1;
            }
        }
    }
}

