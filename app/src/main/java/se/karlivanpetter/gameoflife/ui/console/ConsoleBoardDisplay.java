package se.karlivanpetter.gameoflife.ui.console;

import se.karlivanpetter.gameoflife.core.Board;
import se.karlivanpetter.gameoflife.core.Cell;
import se.karlivanpetter.gameoflife.ui.BoardDisplay;

import java.util.stream.IntStream;

public class ConsoleBoardDisplay implements BoardDisplay {
    @Override
    public void display(Board board) {
        Cell[][] grid = board.getGrid();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j].isAlive()) {
                    stringBuilder.append(" * ");
                } else {
                    stringBuilder.append("   ");
                }
            }
            stringBuilder.append('\n');
        }

        System.out.println(stringBuilder.toString());
    }
}
