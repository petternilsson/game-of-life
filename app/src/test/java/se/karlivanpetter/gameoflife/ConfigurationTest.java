package se.karlivanpetter.gameoflife;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigurationTest {

    @Test
    void testParseConfig() {
        int rows = 10;
        int columns = 10;
        double p = 0.3D;
        int iterations = 10;

        String[] args = new String[] {String.valueOf(rows), String.valueOf(columns), String.valueOf(p), String.valueOf(iterations)};
        Configuration parse = Configuration.parse(args);
        assertThat(parse.getRows()).isEqualTo(rows);
        assertThat(parse.getColumns()).isEqualTo(columns);
        assertThat(parse.getProbability()).isEqualTo(p);
        assertThat(parse.getIterations()).isEqualTo(iterations);
    }

    @Test
    void testMissingArguments() {
        assertThrows(IllegalArgumentException.class, () -> Configuration.parse(new String[]{}));
    }

}