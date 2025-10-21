import java.util.Scanner;

public class startMenu {
    /**
     * Enumerator for all menu options
     */
    enum MenuOptions {
        USER_CONFIGURATION("|| 1. USER CONFIGURATION"),
        EXIT_APPLICATION("|| 0. EXIT APPLICATION");

        private final String text;
        MenuOptions(String text) { this.text = text; }
        public String getText() { return text; }
    }

    static Scanner scanner = new Scanner(System.in);

    public static void console() {
        int input = -1;

        while (input != 0) {
            Utility.clearScreen();
            System.out.println("===================================================");
            System.out.println("         Welcome to our Virtual Library            ");
            System.out.println("===================================================");
            System.out.println(MenuOptions.USER_CONFIGURATION.getText());
            System.out.println(MenuOptions.EXIT_APPLICATION.getText());
            System.out.println("===================================================");
            System.out.print(">> ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                Utility.pause(100);
                continue;
            }

            input = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (input) {
                case 1 -> UserCreation.userConfig();
                case 0 -> System.out.println("Exiting program. Goodbye!");
                default -> {
                    System.out.println("Invalid option. Please try again.");
                    Utility.pause(100);
                }
            }
        }
    }
}
