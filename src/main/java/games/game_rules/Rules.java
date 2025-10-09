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

    public Cell findAlignedCells(Cell[][] table, int size, int symbolsRequired) {
        // rows
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= size - symbolsRequired; j++) {
                String rep = table[i][j].getRepresentation();
                if (rep.isBlank()) continue;

                boolean aligned = true;
                List<int[]> coords = new ArrayList<>();
                coords.add(new int[]{i, j});

                for (int k = 1; k < symbolsRequired; k++) {
                    if (!rep.equals(table[i][j + k].getRepresentation())) {
                        aligned = false;
                        break;
                    }
                    coords.add(new int[]{i, j + k});
                }
                if (aligned) return new Cell(rep, coords);
            }
        }

        // columns
        for (int j = 0; j < size; j++) {
            for (int i = 0; i <= size - symbolsRequired; i++) {
                String rep = table[i][j].getRepresentation();
                if (rep.isBlank()) continue;

                boolean aligned = true;
                List<int[]> coords = new ArrayList<>();
                coords.add(new int[]{i, j});

                for (int k = 1; k < symbolsRequired; k++) {
                    if (!rep.equals(table[i + k][j].getRepresentation())) {
                        aligned = false;
                        break;
                    }
                    coords.add(new int[]{i + k, j});
                }
                if (aligned) return new Cell(rep, coords);
            }
        }

        // main diagonal
        for (int i = 0; i <= size - symbolsRequired; i++) {
            String rep = table[i][i].getRepresentation();
            if (rep.isBlank()) continue;
            boolean aligned = true;
            List<int[]> coords = new ArrayList<>();
            coords.add(new int[]{i, i});
            for (int k = 1; k < symbolsRequired; k++) {
                if (!rep.equals(table[i + k][i + k].getRepresentation())) {
                    aligned = false;
                    break;
                }
                coords.add(new int[]{i + k, i + k});
            }
            if (aligned) return new Cell(rep, coords);
        }

        // anti-diagonal
        for (int i = 0; i <= size - symbolsRequired; i++) {
            String rep = table[i][size - 1 - i].getRepresentation();
            if (rep.isBlank()) continue;
            boolean aligned = true;
            List<int[]> coords = new ArrayList<>();
            coords.add(new int[]{i, size - 1 - i});
            for (int k = 1; k < symbolsRequired; k++) {
                if (!rep.equals(table[i + k][size - 1 - (i + k)].getRepresentation())) {
                    aligned = false;
                    break;
                }
                coords.add(new int[]{i + k, size - 1 - (i + k)});
            }
            if (aligned) return new Cell(rep, coords);
        }

        return new Cell(null, List.of());
    }




}

