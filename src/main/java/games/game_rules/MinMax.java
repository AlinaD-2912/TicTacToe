package games.game_rules;

import games.Coord;
import games.game_engine.Board;
import games.game_engine.Cell;
import games.players.ArtificialPlayer;
import games.players.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMax {

    private static final int MAX_DEPTH = 6;
    private Rules rules;
    private Board board;
    private int size = 3;

    public MinMax() {
        this.rules = new Rules();
        board = new Board(3, 3);
    }


    // explore all possible moves, simulates the players response, returns score
    private int miniMax(Cell[][] table, int depth, boolean isMaximizing, Player artificialPlayer, Player opponent) {
        // si fin de jeu
            // si gagne => return 10
            // si perdu => return -10
            // sinon => return 0

        Cell winner = rules.findAlignedCells(table, board.getSizeX(), board.getSizeY(), size);
        if (winner != null) {
            if (winner.getRepresentation().equals(artificialPlayer.getRepresentation())) { return 99;}
            else {return -99;}
        }

        // si max depth
            // return evaluate
        if (depth == 0 || rules.isBoardFull(size, table)) {
            return 0;
        }

        List<Coord> coords = new ArrayList<>();
        for (int i = 0; i < board.getSizeX(); i++) {
            for (int j = 0; j < board.getSizeY(); j++) {
                if (table[i][j].isEmpty()) {
                    coords.add(new Coord(i, j));
                }
            }
        }

        Collections.shuffle(coords);

        // artificial player's turn
        if (isMaximizing) {
            int best = Integer.MIN_VALUE;

            for (Coord move : coords) {
                int j = move.col();
                int i =  move.row();
                table[i][j].setRepresentation(artificialPlayer.getRepresentation());
                int value = miniMax(table, depth - 1, false, artificialPlayer, opponent);
                table[i][j].clear();
                best = Math.max(best, value);

            }

            return best;
            // players turn
        } else {
            int best = Integer.MAX_VALUE;
            for (Coord move : coords) {
                int i = move.row();
                int j = move.col();
                table[i][j].setRepresentation(artificialPlayer.getRepresentation());
                int value = miniMax(table, depth - 1, true, artificialPlayer, opponent);
                table[i][j].clear();
                best = Math.min(best, value);
            }
            return best;
        }
    }

    // loops over empty cells, uses minmax algorithm, pciks the best move
    public Coord getBestMove(Cell[][] table, Player artificialPlayer, Player opponent) {
        int bestValue = Integer.MIN_VALUE;
        Coord bestMove = null;

        List<Coord> moves = new ArrayList<>();
        for (int i = 0; i < board.getSizeX(); i++) {
            for (int j = 0; j < board.getSizeY(); j++) {
                if (table[i][j].isEmpty()) {
                    moves.add(new Coord(i, j));
                }
            }
        }

        Collections.shuffle(moves);

        for (Coord move : moves) {
            int i = move.col();
            int j = move.row();
            table[i][j].setRepresentation(artificialPlayer.getRepresentation());

            int moveValue = miniMax(table, MAX_DEPTH, false, artificialPlayer, opponent);
            table[i][j].clear();

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMove = move;
            }
        }

        return bestMove != null ? bestMove : new Coord(-1, -1);

    }
}