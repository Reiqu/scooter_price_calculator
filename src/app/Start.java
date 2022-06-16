package app;

import console.Console;
import ui.UserInterface;

public class Start {
    public static void main(String... args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "ui": {
                    System.out.println("Hello UI!");
                    UserInterface ui = new UserInterface();;
                    break;
                }
                case "console": {
                    System.out.println("Hello Console!");
                    new Console();
                    break;
                }
                default: {
                    System.out.println("Hello default! Switching to console...");
                    new Console();
                    break;
                }
            }
        } else {
            System.out.println("Hello default! Switching to console...");
            new Console();
        }

    }
}
