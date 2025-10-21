import java.util.ArrayList;
import java.util.Scanner;

public class UserCreation {
    static ArrayList<LibraryUser> users = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void userConfig() {
        int choice = -1;
        while (choice != 0) {
            Utility.clearScreen();
            System.out.println("                       USER CONFIGURATION MENU                       ");
            System.out.println("=====================================================================");
            System.out.println("|| 1. VIEW ALL USERS");
            System.out.println("|| 2. CREATE NEW USER");
            System.out.println("|| 0. RETURN TO MAIN MENU");
            System.out.println("=====================================================================");
            System.out.print(">> ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine();
                Utility.pause(100);
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewUsers();
                case 2 -> userCreatorMenu();
                case 0 -> System.out.println("Returning to main menu...");
                default -> {
                    System.out.println("Invalid choice. Try again.");
                    Utility.pause(100);
                }
            }
        }
    }

    public static void viewUsers() {
        Utility.clearScreen();
        System.out.println("                              CURRENT USERS                            ");
        System.out.println("======================================================================");
        System.out.printf("| %-20s | %-10s | %-10s |%n", "Name", "Banner ID", "Role");
        System.out.println("----------------------------------------------------------------------");
        if (users.isEmpty()) { System.out.println("| No users found. Create one to get started!"); }
        else { for (LibraryUser user : users) { System.out.println(user); } }
        System.out.println("======================================================================");
        Utility.pause(200);
    }

    public static void userCreatorMenu() {
        Utility.clearScreen();
        System.out.println("                        CHARACTER CREATION                            ");
        System.out.println("======================================================================");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter role (Student / Librarian / Admin): ");
        String role = scanner.nextLine().trim().toUpperCase();

        if (firstName.isEmpty() || lastName.isEmpty() || role.isEmpty()) {
            System.out.println("Invalid entry. All fields are required!");
            Utility.pause(150);
            return;
        }

        LibraryUser newUser = new LibraryUser(firstName, lastName, role);
        users.add(newUser);

        System.out.println("\nCreating user...");
        Utility.pause(100);
        System.out.println("User successfully created!");
        Utility.pause(100);
    }
}
