package se.karlivanpetter.gameoflife.core;

import org.junit.jupiter.api.Test;
import se.karlivanpetter.gameoflife.core.Board;
import se.karlivanpetter.gameoflife.core.Cell;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BoardTest {
    @Test
    void testInit() {
        int rows = 10;
        int columns = 9;
        double probability = 1.0D;
        Board board = Board.init(rows, columns, probability);
        Cell[][] grid = board.getGrid();

        assertThat(grid.length).isEqualTo(rows);
        assertThat(grid[0].length).isEqualTo(columns);
    }

    @Test
    void testIncorrectSize() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Board.init(0, 0, 1));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> Board.init(-1, -1, 1));
    }

    @Test
    void testInit100percentProbability() {
        int rows = 10;
        int columns = 9;
        double probability = 1.0D;
        Board board = Board.init(rows, columns, probability);
        assertAllCellsAlive(board.getGrid());
    }

    @Test
    void testInit0percentProbability() {
        int rows = 10;
        int columns = 9;
        double probability = 0.0D;
        Board board = Board.init(rows, columns, probability);
        assertAllCellsDead(board.getGrid());
    }

    @Test
    void testGetNeighboursOutOfBounds() {
        int rows = 3;
        int columns = 3;
        double probability = 1.0D;
        Board board = Board.init(rows, columns, probability);
        int numberOfLivingNeighbours = board.getNumberOfLivingNeighbours(-1, -1);
        assertThat(numberOfLivingNeighbours).isZero();
    }

    @Test
    void testGetNeighboursInCorners() {
        int rows = 3;
        int columns = 3;
        double probability = 1.0D;
        Board board = Board.init(rows, columns, probability);

        int nw = board.getNumberOfLivingNeighbours(0, 0);
        assertThat(nw).isEqualTo(3);

        int ne = board.getNumberOfLivingNeighbours(0, 2);
        assertThat(ne).isEqualTo(3);

        int sw = board.getNumberOfLivingNeighbours(2, 0);
        assertThat(sw).isEqualTo(3);

        int se = board.getNumberOfLivingNeighbours(2, 2);
        assertThat(se).isEqualTo(3);
    }

    @Test
    void testGetNeighboursCenter() {
        int rows = 3;
        int columns = 3;
        double probability = 1.0D;
        Board board = Board.init(rows, columns, probability);

        int center = board.getNumberOfLivingNeighbours(1, 1);
        assertThat(center).isEqualTo(8);
    }

    @Test
    void testNextGeneration() {
        Board firstGeneration = Board.init(3, 3, 1);
        Board secondGeneration = firstGeneration.getNextGeneration();
        Board thirdGeneration = secondGeneration.getNextGeneration();

        assertAllCellsAlive(firstGeneration.getGrid());

        //On second generations only corners are alive
        assertThat(secondGeneration.getGrid()[0][0].isAlive()).isTrue();
        assertThat(secondGeneration.getGrid()[0][2].isAlive()).isTrue();
        assertThat(secondGeneration.getGrid()[2][0].isAlive()).isTrue();
        assertThat(secondGeneration.getGrid()[2][2].isAlive()).isTrue();

        assertThat(secondGeneration.getGrid()[0][1].isAlive()).isFalse();
        assertThat(secondGeneration.getGrid()[1][0].isAlive()).isFalse();
        assertThat(secondGeneration.getGrid()[1][1].isAlive()).isFalse();
        assertThat(secondGeneration.getGrid()[1][2].isAlive()).isFalse();
        assertThat(secondGeneration.getGrid()[2][1].isAlive()).isFalse();

        // On third generation all cells are dead
        assertAllCellsDead(thirdGeneration.getGrid());
    }

    @Test
    void testDeadGrid() {
        Board firstGeneration = Board.init(10, 10, 0);
        Board secondGeneration = firstGeneration.getNextGeneration();
        Board thirdGeneration = secondGeneration.getNextGeneration();

        assertAllCellsDead(secondGeneration.getGrid());
        assertAllCellsDead(thirdGeneration.getGrid());
    }

    private static void assertAllCellsDead(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                assertThat(grid[i][j].isAlive()).isFalse();
            }
        }
    }

    private static void assertAllCellsAlive(Cell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                assertThat(grid[i][j].isAlive()).isTrue();
            }
        }
    }
}