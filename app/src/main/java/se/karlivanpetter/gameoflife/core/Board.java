package se.karlivanpetter.gameoflife.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {

    private final int rows;
    private final int columns;
    private final Cell[][] grid;

    public Board(Cell[][] grid) {
        this.grid = grid;
        rows = grid.length;
        columns = grid[0].length;
    }

    public static Board init(int rows, int columns, double p) {
        if (rows < 1 || columns < 1) {
            throw new IllegalArgumentException();
        }

        Cell[][] grid = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new Cell(Math.random() <= p);
            }
        }

        return new Board(grid);
    }

    public Cell[][] getGrid() {
        return this.grid;
    }

    public Board getNextGeneration() {
        Cell[][] newGrid = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int numberOfLivingNeighbours = getNumberOfLivingNeighbours(i, j);
                Cell newCell = grid[i][j].evolve(numberOfLivingNeighbours);
                newGrid[i][j] = newCell;
            }

        }
        return new Board(newGrid);
    }


    public int getNumberOfLivingNeighbours(int row, int column) {
        if (!isWithinBounds(row, column)) {
            return 0;
        }

        Collection<Coordinate> neighbouringCoordinates = getNeighbouringCoordinates(row, column);
        AtomicInteger numberOfLivingNeighbours = new AtomicInteger();

        neighbouringCoordinates.forEach(coordinate -> {
            if (isAlive(coordinate.getRow(), coordinate.getColumn())) {
                numberOfLivingNeighbours.incrementAndGet();
            }
        });

        return numberOfLivingNeighbours.get();
    }

    public Collection<Coordinate> getNeighbouringCoordinates(int row, int column) {
        Collection<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(new Coordinate(row - 1, column - 1));
        coordinates.add(new Coordinate(row - 1, column));
        coordinates.add(new Coordinate(row - 1, column + 1));

        coordinates.add(new Coordinate(row, column - 1));
        coordinates.add(new Coordinate(row, column + 1));

        coordinates.add(new Coordinate(row + 1, column - 1));
        coordinates.add(new Coordinate(row + 1, column));
        coordinates.add(new Coordinate(row + 1, column + 1));

        return coordinates;
    }

    public boolean isAlive(int row, int column) {
        return isWithinBounds(row, column) && grid[row][column].isAlive();
    }

    public boolean isWithinBounds(int row, int column) {
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }

}
