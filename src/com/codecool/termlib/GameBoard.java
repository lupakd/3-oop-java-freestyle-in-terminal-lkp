package com.codecool.termlib;
import java.util.Random;

class GameBoard {
    private final static int WIDTH = 40;
    private final static int HEIGHT = 40;
    private String[][] grid = new String[HEIGHT][WIDTH];
    private static String fillChar = Character.toString('\u2588');

    String[][] init() {
        return coloringGrid();
    }

    void printBoard(String[][] grid) {
        for (String[] i : grid) {
            for (String j : i) {
                System.out.print(j);
                System.out.print(' ');
            }
            System.out.print("\u001B[0m" + "\n"); // need to reset background for last chars in line
        }
    }

    private String[][] coloringGrid() {
        for (int i = 1; i < grid.length; i += 2) {
            for (int j = 1; j < grid.length; j += 2) {
                if (i == 1 && j > 1 && j < grid.length - 2) {
                    color2x2(grid, "31", "41", i, j);
                } else if (i == grid.length - 1 && j > 1 && j < grid.length - 2) {
                    color2x2(grid, "32", "42", i, j);
                } else if (j == 1 && i > 1 && i < grid.length - 2) {
                    color2x2(grid, "35", "45", i, j);
                } else if (j == grid.length - 1 && i > 1 && i < grid.length - 2) {
                    color2x2(grid, "34", "44", i, j);
                } else {
                    color2x2(grid, "30", "40", i, j);
                }
            }
        }
        return grid;
    }

    String[][] color2x2(String[][] myGrid, String fg, String bg, int gridI, int gridJ) {
        myGrid[gridI][gridJ] = "\u001B[" + bg + ";" + fg + "m" + fillChar;
        myGrid[gridI - 1][gridJ] = "\u001B[" + bg + ";" + fg + "m" + fillChar;
        myGrid[gridI][gridJ - 1] = "\u001B[" + bg + ";" + fg + "m" + fillChar;
        myGrid[gridI - 1][gridJ - 1] = "\u001B[" + bg + ";" + fg + "m" + fillChar;
        return myGrid;
    }

    String[][] color4x4(String[][] myGrid, String fg, String bg, int gridI, int gridJ) {
        for (int i=gridI - 2;i<gridI + 2;i++) {
            for (int j=gridJ - 2;j<gridJ + 2;j++) {
                myGrid[i][j] = "\u001B[" + bg + ";" + fg + "m" + fillChar;
            }
        }
        return myGrid;
    }
}
