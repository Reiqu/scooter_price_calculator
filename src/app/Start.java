package app;

import console.Console;
import ui.UserInterface;

public class Start {
    public static void main(String... args) {

        /*
        * Startet je nach Argument die Konsolen- oder Fensterversion
        */
        if (args.length > 0) {
            switch (args[0]) {
                case "ui" -> {
                    System.out.println("Hello UI!");
                    new UserInterface();
                }
                case "console" -> {
                    System.out.println("Hello Console!");
                    new Console();
                }
                default -> {
                    System.out.println("Hello default! Switching to console...");
                    new Console();
                }
            }
        } else {
            System.out.println("Hello default! Switching to console...");
            new Console();
        }

    }
}
