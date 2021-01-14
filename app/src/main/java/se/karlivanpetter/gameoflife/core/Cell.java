package se.karlivanpetter.gameoflife.core;

public class Cell {
    private boolean isAlive;

    public Cell(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public Cell evolve(int numberOfNeighbours) {
        if (numberOfNeighbours == 2) {
            return new Cell(isAlive);
        } else if (numberOfNeighbours == 3) {
            return new Cell(true);
        } else {
            return new Cell(false);
        }
    }




}
