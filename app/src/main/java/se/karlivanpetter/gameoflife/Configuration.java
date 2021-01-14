package se.karlivanpetter.gameoflife;

public class Configuration {
    private final int rows;
    private final int columns;
    private final double probability;
    private final int iterations;

    private Configuration(int rows, int columns, double probability, int iterations) {
        this.rows = rows;
        this.columns = columns;
        this.probability = probability;
        this.iterations = iterations;
    }

    public static Configuration parse(String[] arguments) {
        if (arguments.length != 4) {
            throw new IllegalArgumentException();
        }

        int rows = Integer.parseInt(arguments[0]);
        int columns = Integer.parseInt(arguments[1]);
        double probability = Double.parseDouble(arguments[2]);
        int iterations = Integer.parseInt(arguments[3]);

        return new Configuration(rows, columns, probability, iterations);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double getProbability() {
        return probability;
    }

    public int getIterations() {
        return iterations;
    }
}
