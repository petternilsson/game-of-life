package se.karlivanpetter.gameoflife.core;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    @Test
    void testUnderpopulation() {
        Cell cell = new Cell(true);
        assertThat(cell.evolve(1).isAlive()).isFalse();
    }

    @Test
    void testLivesOn() {
        Cell cell = new Cell(true);
        assertThat(cell.evolve(2).isAlive()).isTrue();
        assertThat(cell.evolve(3).isAlive()).isTrue();
    }

    @Test
    void testOverpopulation() {
        Cell cell = new Cell(true);
        assertThat(cell.evolve(3).isAlive()).isTrue();
        assertThat(cell.evolve(4).isAlive()).isFalse();
    }

    @Test
    void testReproduction() {
        Cell cell = new Cell(false);
        assertThat(cell.evolve(2).isAlive()).isFalse();
        assertThat(cell.evolve(3).isAlive()).isTrue();
        assertThat(cell.evolve(4).isAlive()).isFalse();
    }
}