import java.util.Scanner;

public class startMenu {
    // Enum for menu options
    enum MenuOptions {
        // =========================== OPTIONS =================================//
        USER_CONFIGURATION("|| 1. USER CONFIGURATION"),
        EXIT_APPLICATION("|| 0. EXIT APPLICATION");
        // ======================================================================//

        private final String text;
        MenuOptions(String text) { this.text = text; }
        public String getText() { return text; }
    }

    public static void console() {
        Scanner scanner = new Scanner(System.in);
        int input = -1;

        while (input != 0) {
            System.out.println("===================================================");
            System.out.println("         Welcome to our Virtual Library            ");
            System.out.println("===================================================");
            System.out.println(MenuOptions.USER_CONFIGURATION.getText());
            System.out.println(MenuOptions.EXIT_APPLICATION.getText());
            System.out.println("===================================================");
            System.out.print(">> ");

            // get new input each loop
            input = scanner.nextInt();

            System.out.println("USER_INPUT: " + input);

            // handle choices
            switch (input) {
                case 1:
                    System.out.println("Opening User Configuration...");
                    userConfig();
                    break;
                case 0: System.out.println("Exiting program. Goodbye!"); break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void userConfig()
    {
        LibraryUser user = new LibraryUser();

        System.out.println("                       CURRENT USERS                          ");
        System.out.println("==============================================================");
        System.out.printf("| %-15s | %-10s | %-10s |%n", "Name", "Banner ID", "Role");
        System.out.println("--------------------------------------------------------------");
        System.out.println(user);
        System.out.println("--------------------------------------------------------------");
        System.out.println();
    }
}