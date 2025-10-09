package games.game_rules;


import games.game_engine.Cell;

import java.util.ArrayList;
import java.util.List;

public class Rules {

    // Check the fullness of the board
    public boolean isBoardFull(int size, Cell[][] table) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j].getRepresentation().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

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
