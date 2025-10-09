package games.game_rules;

import games.game_engine.Board;
import games.game_engine.Cell;
import games.players.ArtificialPlayer;
import games.players.Player;

public class MinMax {

    private static final int MAX_DEPTH = 6;
    private Rules rules;
    private Board board;
    private ArtificialPlayer oponent;
    private int size = 3;

    public MinMax() {
        this.rules = new Rules();
        board = new Board(3, 3);
    }

    // to know which board positions good or bad
    private int evaluateBoard(Cell[][] table, Player artificialPlayer) {
        // Si gagné
        // return 50
        // Si draw
        //return 0
        // si perdu
        //return -50

        // checks the full alignment needed to win, determines who wins
        Cell fullWin = rules.findAlignedCells(table, board.getSizeX(), board.getSizeY(), size);
        if (fullWin != null) {
            // artificial player wins
            if (fullWin.getRepresentation().equals(artificialPlayer.getRepresentation())) {
                return 10;
            }
            // player wins
            else {
                return -10;
            }
        }
        // checks the alignment minus 1, so only 1 step left to win, and for whom
        Cell almostWon = rules.findAlignedCells(table, board.getSizeX(), board.getSizeY() - 1, size);
        if (almostWon != null) {
            if (almostWon.getRepresentation().equals(artificialPlayer.getRepresentation())) {
                return 5; // artificial player almost wins
            } else {
                return -8; // player almost wins
            }
        }
        return 0; // draw
    }

    // explore all possible moves, simulates the players response, returns score
    private int miniMax(Cell[][] table, int depth, boolean isMaximizing, Player artificialPlayer, Player opponent) {
        // evaluate the current board
        int score = evaluateBoard(table, artificialPlayer);
        if (Math.abs(score) == 10 || depth == 0 || rules.isBoardFull(board.getSizeX(), table)) {
            return score;
        }
        // artificial player's turn
        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < board.getSizeX(); i++) {
                for (int j = 0; j < board.getSizeY(); j++) {
                    if (table[i][j].isEmpty()) {
                        board.setOwner(i, j, artificialPlayer);
                        best = Math.max(best, miniMax(table, depth - 1, false, artificialPlayer, opponent));
                        // clear the simulation from the table
                        board.getTable()[i][j].clear();
                    }
                }

            }
            return best;
            // players turn
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < board.getSizeX(); i++) {
                for (int j = 0; j < board.getSizeY(); j++) {
                    if (table[i][j].isEmpty()) {
                        board.setOwner(i, j, opponent);
                        best = Math.min(best, miniMax(table, depth - 1, true, artificialPlayer, opponent));
                        // clear the simulation from the table
                        board.getTable()[i][j].clear();
                    }
                }
            }
            return best;
        }
    }

    // loops over empty cells, uses minmax algorithm, pciks the best move
    public int[] getBestMove(Cell[][] table, Player artificialPlayer, Player opponent) {
        int bestValue = Integer.MIN_VALUE;
        // impossible coordinates
        int[] bestMove = {-1, -1};
        for (int i = 0; i < board.getSizeX(); i++) {
            for (int j = 0; j < board.getSizeY(); j++) {
                // for each empty cell
                if (table[i][j].isEmpty()) {
                    // simulates the move, modifies the board temporaly
                    board.setOwner(i, j, artificialPlayer);
                    // simulates what will happen next after the previous move, rates how good the move
                    int moveValue = miniMax(table, MAX_DEPTH, false, artificialPlayer, opponent);
                    // undo the simulation
                    board.getTable()[i][j].clear();
                    // replaces bestValue with the highest score of moveValue
                    if (moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;

    }
}