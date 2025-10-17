/*
 * Name of the class: Rules
 *
 * Description: this class is a collection of rules (functions) that checks the current game state, the board, cells.
 *              In any time new rules or conditions can be added here to be reused n the next game.
 *
 * Version: 1.0
 *
 * Date: 13/10/2025
 *
 * Copyright: moi
 */

package model.board;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    /**
     *  Checks the fullness of the board
     */
    public boolean isBoardFull(int sizeX, int sizeY, Cell[][] table) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (table[i][j].getRepresentation().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     *  Finds alignments horizontally, vertically and by diagonals
     */
    public Cell findAlignedCells(Cell[][] table, int sizeX, int sizeY, int symbolsRequired) {
        // ---- ROWS ----
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j <= sizeY - symbolsRequired; j++) {
                String rep = table[i][j].getRepresentation();
                if (rep.isBlank()) continue;

                boolean aligned = true;
                List<int[]> coords = new ArrayList<>();
                coords.add(new int[]{i, j});

                for (int k = 1; k < symbolsRequired; k++) {
                    if (j + k >= sizeY || !rep.equals(table[i][j + k].getRepresentation())) {
                        aligned = false;
                        break;
                    }
                    coords.add(new int[]{i, j + k});
                }
                if (aligned) return new Cell(rep, coords);
            }
        }

        // ---- COLUMNS ----
        for (int j = 0; j < sizeY; j++) {
            for (int i = 0; i <= sizeX - symbolsRequired; i++) {
                String rep = table[i][j].getRepresentation();
                if (rep.isBlank()) continue;

                boolean aligned = true;
                List<int[]> coords = new ArrayList<>();
                coords.add(new int[]{i, j});

                for (int k = 1; k < symbolsRequired; k++) {
                    if (i + k >= sizeX || !rep.equals(table[i + k][j].getRepresentation())) {
                        aligned = false;
                        break;
                    }
                    coords.add(new int[]{i + k, j});
                }
                if (aligned) return new Cell(rep, coords);
            }
        }

        // ---- MAIN DIAGONAL (\) ----
        for (int i = 0; i <= sizeX - symbolsRequired; i++) {
            for (int j = 0; j <= sizeY - symbolsRequired; j++) {
                String rep = table[i][j].getRepresentation();
                if (rep.isBlank()) continue;

                boolean aligned = true;
                List<int[]> coords = new ArrayList<>();
                coords.add(new int[]{i, j});

                for (int k = 1; k < symbolsRequired; k++) {
                    if (i + k >= sizeX || j + k >= sizeY ||
                            !rep.equals(table[i + k][j + k].getRepresentation())) {
                        aligned = false;
                        break;
                    }
                    coords.add(new int[]{i + k, j + k});
                }
                if (aligned) return new Cell(rep, coords);
            }
        }

        // ---- ANTI-DIAGONAL (/) ----
        for (int i = 0; i <= sizeX - symbolsRequired; i++) {
            for (int j = symbolsRequired - 1; j < sizeY; j++) {
                String rep = table[i][j].getRepresentation();
                if (rep.isBlank()) continue;

                boolean aligned = true;
                List<int[]> coords = new ArrayList<>();
                coords.add(new int[]{i, j});

                for (int k = 1; k < symbolsRequired; k++) {
                    if (i + k >= sizeX || j - k < 0 ||
                            !rep.equals(table[i + k][j - k].getRepresentation())) {
                        aligned = false;
                        break;
                    }
                    coords.add(new int[]{i + k, j - k});
                }
                if (aligned) return new Cell(rep, coords);
            }
        }

        // No alignment found
        return new Cell(null, List.of());
    }

}
