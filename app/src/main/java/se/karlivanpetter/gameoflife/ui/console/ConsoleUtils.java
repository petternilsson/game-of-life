package se.karlivanpetter.gameoflife.ui.console;

public class ConsoleUtils {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
