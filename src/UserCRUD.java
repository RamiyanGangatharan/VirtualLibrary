import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserCRUD {
    private static ArrayList<LibraryUser> users = new ArrayList<>();
    private static final USER_CSVHandler csvHandler = new USER_CSVHandler();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> VALID_ROLES = Arrays.asList("Admin", "Librarian", "Student", "Member");

    // ===================== INIT =====================

    public static void init() {
        List<LibraryUser> loaded = csvHandler.readCSV();
        users = new ArrayList<>(loaded != null ? loaded : new ArrayList<>());
    }

    // ===================== MENU =====================

    public static void userConfig() {
        int choice = -1;
        while (choice != 0) {
            UI.printMenu("User Configuration Menu", UI.UserConfigOps.values());

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

    // ===================== VIEW =====================

    public static void viewUsers() {
        UI.UserListUI();
        if (users.isEmpty()) {
            System.out.println("| No users found. Create one to get started!");
        } else {
            long startTime = System.currentTimeMillis();
            SortUsers.mergeSort(users, 0, users.size() - 1);
            long endTime = System.currentTimeMillis();

            for (LibraryUser user : users) { System.out.println(user); }
            System.out.println("Total time taken: " + (endTime - startTime) + "ms");
        }
        System.out.println("======================================================================");
        Utility.pause(200);
    }

    // ===================== CREATE =====================

    public static void userCreatorMenu() {
        System.out.println("\n=== Create a New Library User ===");

        String firstName;
        String lastName;
        String normalizedRole;

        while (true) {
            System.out.print("Enter first name: ");
            firstName = scanner.nextLine().trim();
            if (firstName.isEmpty()) {
                System.out.println("First name cannot be empty. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("Enter last name: ");
            lastName = scanner.nextLine().trim();
            if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("Enter role (Admin, Librarian, Student, Member): ");
            String roleInput = scanner.nextLine().trim();
            if (roleInput.isEmpty()) {
                System.out.println("Role cannot be empty. Try again.");
                Utility.pause(150);
                continue;
            }

            normalizedRole = Utility.capitalize(roleInput);
            if (!VALID_ROLES.contains(normalizedRole)) {
                System.out.println("Invalid role. Must be one of: " + VALID_ROLES);
                Utility.pause(150);
                continue;
            }
            break;
        }

        LibraryUser newUser = new LibraryUser(firstName, lastName, normalizedRole);
        users.add(newUser);
        csvHandler.appendRow(newUser);

        System.out.println("\nCreating user...");
        Utility.pause(100);
        System.out.println("User successfully created!");
        Utility.pause(100);
    }

    // ===================== DELETE =====================

    public static void userDeletionMenu() {
        System.out.println("\n=== DELETE USER ===");
        viewUsers();

        if (users.isEmpty()) {
            System.out.println("No users to delete.");
            Utility.pause(200);
            return;
        }

        System.out.print("Enter the Banner ID of the user to delete: ");
        String input = scanner.nextLine().trim();
        int idToDelete;
        try {
            idToDelete = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Must be a number.");
            Utility.pause(200);
            return;
        }

        LibraryUser userToRemove = users.stream()
                .filter(u -> u.getBannerID() == idToDelete)
                .findFirst()
                .orElse(null);

        if (userToRemove == null) {
            System.out.println("No user found with that Banner ID.");
            return;
        }

        System.out.print("Are you sure you want to delete this user? (Y/N): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        System.out.println("Removing: " + userToRemove);
        users.remove(userToRemove);
        csvHandler.saveAll(users); // overwrite file
        System.out.println("User successfully deleted!");
        Utility.pause(200);
    }

    // ===================== UPDATE =====================

    public static void userUpdateMenu() {
        System.out.println("\n=== UPDATE USER ===");
        viewUsers();

        if (users.isEmpty()) {
            System.out.println("No users to update.");
            Utility.pause(200);
            return;
        }

        System.out.print("Enter the Banner ID of the user to update: ");
        String input = scanner.nextLine().trim();

        int idToUpdate;
        try {
            idToUpdate = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Must be a number.");
            Utility.pause(200);
            return;
        }

        LibraryUser userToUpdate = users.stream()
                .filter(u -> u.getBannerID() == idToUpdate)
                .findFirst()
                .orElse(null);

        if (userToUpdate == null) {
            System.out.println("No user found with that Banner ID.");
            Utility.pause(200);
            return;
        }

        System.out.println("What needs updating?");
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
                String normalizedRole = Utility.capitalize(newRole);
                if (!VALID_ROLES.contains(normalizedRole)) {
                    System.out.println("Invalid role. Must be one of: " + VALID_ROLES);
                    return;
                }
                userToUpdate.setRole(normalizedRole);
            }
            default -> {
                System.out.println("Invalid option.");
                return;
            }
        }

        csvHandler.saveAll(users); // overwrite file with new data
        System.out.println("User successfully updated!");
        Utility.pause(200);
    }
}
