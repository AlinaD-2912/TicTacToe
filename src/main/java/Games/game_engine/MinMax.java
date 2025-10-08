package Games.game_engine;

import Games.game_rules.Rules;
import Games.players.ArtificialPlayer;
import Games.players.Player;

public class MinMax {

    private static final int MAX_DEPTH = 6;
    private Rules rules;
    private Board board;
    private ArtificialPlayer oponent;

    public MinMax() {
        this.rules = new Rules();
        board = new Board();
    }

    private int evaluateBoard (Cell[][] table, Player artificialPlayer) {
        // checks the full alignment needed to win, determines who wins
        Cell fullWin = rules.findAlignedCells(table, board.getSize(), board.getSize());
        if (fullWin != null) {
            // artificial player wins
            if (fullWin.getRepresentation().equals(artificialPlayer.getRepresentation())) { return 10; }
            // player wins
            else { return -10; }
        }
        // checks the alignment minus 1, so only 1 step left to win, and for whom
        Cell almostWon = rules.findAlignedCells(table, board.getSize(), board.getSize()-1);
        if (almostWon != null) {
            if (almostWon.getRepresentation().equals(artificialPlayer.getRepresentation())) { return 5; }
            else { return -5; }
        }
        return 0;
    }
    
    private int miniMax(Cell[][] table, int depth, boolean isMaximizing, Player artificialPlayer, Player oponent) {
        int score = evaluateBoard(table, artificialPlayer);
        if (Math.abs(score) == 10 || depth == 0 || rules.isBoardFull(board.getSize(), table)) {return score;}
        if (isMaximizing) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    if (table[i][j].isEmpty()) {
                        board.setOwner(i, j, artificialPlayer);
                        best = Math.max(best, miniMax(table, depth-1, false, artificialPlayer, oponent));
                        board.getTable()[i][j].clear();
                    }
                }

            } return best;
        } else  {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    if (table[i][j].isEmpty()) {
                        board.setOwner(i, j, oponent);
                        best = Math.min(best, miniMax(table, depth-1, true, artificialPlayer, oponent));
                        board.getTable()[i][j].clear();
                    }
                }
            } return best;
        }
    }

    public  int[] getBestMove(Cell[][] table, Player artificialPlayer, Player oponent) {
        int bestValue = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (table[i][j].isEmpty()) {
                    board.setOwner(i, j, artificialPlayer);
                    int moveValue = miniMax(table, MAX_DEPTH, false, artificialPlayer, oponent);
                    board.getTable()[i][j].clear();
                    if (moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }return bestMove;

    }
}
