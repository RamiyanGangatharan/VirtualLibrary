import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserCreation {
    static ArrayList<LibraryUser> users = new ArrayList<>();

    public static void init() {
        List<LibraryUser> loaded = CSVHandler.readCSV();
        users = new ArrayList<>(loaded != null ? loaded : new ArrayList<>());
    }

    static Scanner scanner = new Scanner(System.in);

    private static final List<String> VALID_ROLES = Arrays.asList("Admin", "Librarian", "Student", "Member");

    public static void userConfig() {
        int choice = -1;
        while (choice != 0) {
            UserInterface.printMenu("User Configuration Menu", UserInterface.UserConfigOps.values());

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
                case 3 -> userDeletionMenu();
                case 4 -> userUpdateMenu();
                case 0 -> System.out.println("Returning to main menu...");
                default -> {
                    System.out.println("Invalid choice. Try again.");
                    Utility.pause(100);
                }
            }
        }
    }

    public static void viewUsers() {
        UserInterface.UserListUI();
        if (users.isEmpty()) {
            System.out.println("| No users found. Create one to get started!");
        } else {
            long startTime = System.currentTimeMillis();
            SortUsers.mergeSort(users, 0, users.size() - 1);
            long endTime = System.currentTimeMillis();

            for (LibraryUser user : users) {
                System.out.println(user);
            }
            System.out.println("Total time taken: " + (endTime - startTime) + "ms");
        }
        System.out.println("======================================================================");
        Utility.pause(200);
    }

    public static void userCreatorMenu() {
        System.out.println("\n=== Create a New Library User ===");

        String firstName;
        String lastName;
        String normalizedRole;

        // Keep asking for input until valid entries are given
        while (true) {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("First name cannot be empty. Try again.");
                Utility.pause(150);
                System.out.println("RESETTING USER CREATION...");
                continue;
            }

            System.out.print("Enter last name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty. Try again.");
                Utility.pause(150);
                System.out.println("RESETTING USER CREATION...");
                continue;
            }

            System.out.print("Enter role (Admin, Librarian, Student, Member): ");
            String roleInput = scanner.nextLine().trim();
            if (roleInput.isEmpty()) {
                System.out.println("Role cannot be empty. Try again.");
                Utility.pause(150);
                System.out.println("RESETTING USER CREATION...");
                continue;
            }

            // Normalize case
            normalizedRole = roleInput.substring(0, 1).toUpperCase() + roleInput.substring(1).toLowerCase();

            // Validate against the list
            if (!VALID_ROLES.contains(normalizedRole)) {
                System.out.println("Invalid role. Must be one of: " + VALID_ROLES);
                Utility.pause(150);
                System.out.println("RESETTING USER CREATION...");
                continue;
            }
            break;
        }

        // Create the user now that all inputs are validated
        LibraryUser newUser = new LibraryUser(firstName, lastName, normalizedRole);
        users.add(newUser);
        CSVHandler.appendRow(newUser);

        System.out.println("\nCreating user...");
        Utility.pause(100);
        System.out.println("User successfully created!");
        Utility.pause(100);
    }

    public static void userDeletionMenu() {
        System.out.println("\n=== DELETE USER ===");
        viewUsers();

        if (users.isEmpty()) {
            System.out.println("No users to delete.");
            Utility.pause(200);
            return;
        }

        System.out.print("Enter the Banner ID of the user to delete: ");
        String tempID_DELETE = scanner.nextLine().trim();

        // Validate numeric input
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(tempID_DELETE);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Must be a number.");
            Utility.pause(200);
            return;
        }

        // Find the user by banner ID
        LibraryUser userToRemove = null;
        for (LibraryUser user : users) {
            if (user.getBannerID() == idToDelete) {
                userToRemove = user;
                break;
            }
        }

        if (userToRemove == null) {
            System.out.println("No user found with that Banner ID.");
        } else {
            System.out.print("Are you sure you want to delete this user? (Y/N): ");
            String confirm = scanner.nextLine().trim();
            if (!confirm.equalsIgnoreCase("Y")) {
                System.out.println("Deletion Cancelled.");
                return;
            }
            else {
                System.out.println("Removing: " + userToRemove);
                users.remove(userToRemove);
                CSVHandler.deleteRow(users);
                System.out.println("User successfully deleted!");
            }
        }

        Utility.pause(200);
    }

    public static void userUpdateMenu() {
        System.out.println("\n=== UPDATE USER ===");
        viewUsers();

        if (users.isEmpty()) {
            System.out.println("No users to update.");
            Utility.pause(200);
            return;
        }

        System.out.print("Enter the Banner ID of the user to update: ");
        String TempID_UPDATE = scanner.nextLine().trim();

        int idToUpdate;
        try {
            idToUpdate = Integer.parseInt(TempID_UPDATE);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Must be a number.");
            Utility.pause(200);
            return;
        }

        LibraryUser userToUpdate = null;
        for (LibraryUser user : users) {
            if (user.getBannerID() == idToUpdate) {
                userToUpdate = user;
                break;
            }
        }

        if (userToUpdate == null) {
            System.out.println("No user found with that Banner ID.");
            Utility.pause(200);
            return;
        }

        System.out.println("What needs updating?: ");
        System.out.println("1. Firstname");
        System.out.println("2. Lastname");
        System.out.println("3. Role");
        System.out.print("Choose an option: ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Returning to menu.");
            scanner.nextLine();
            return;
        }
        int option = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (option) {
            case 1 -> {
                System.out.print("Enter new firstname: ");
                userToUpdate.setFirstName(scanner.nextLine().trim());
            }
            case 2 -> {
                System.out.print("Enter new lastname: ");
                userToUpdate.setLastName(scanner.nextLine().trim());
            }
            case 3 -> {
                System.out.print("Enter new role: ");
                String newRole = scanner.nextLine().trim();
                if (!VALID_ROLES.contains(Utility.capitalize(newRole))) {
                    System.out.println("Invalid role. Must be one of: " + VALID_ROLES);
                    return;
                }
                userToUpdate.setRole(Utility.capitalize(newRole));
            }
            default -> System.out.println("Invalid option.");
        }

        CSVHandler.updateRow(users);
        System.out.println("User successfully updated!");
    }
}
