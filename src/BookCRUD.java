import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookCRUD {
    private static ArrayList<Book> books = new ArrayList<>();
    private static final BOOK_CSVHandler csvHandler = new BOOK_CSVHandler();
    private static final Scanner scanner = new Scanner(System.in);

    public static void init() {
        List<Book> loaded = csvHandler.readCSV();
        books = new ArrayList<>(loaded != null ? loaded : new ArrayList<>());
    }

    public static void bookConfig() {
        int choice = -1;

        while (choice != 0) {
            UI.printMenu("Book Configuration Menu", UI.LibraryConfigOps.values());

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid Input. Try again.");
                scanner.nextLine();
                Utility.pause(100);
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewBooks();
                case 2 -> bookCreatorMenu();
                case 3 -> bookDeletionMenu();
                case 4 -> bookUpdateMenu();
                case 0 -> System.out.println("Returning to main menu...");
                default -> {
                    System.out.println("Invalid choice. Try again");
                    Utility.pause(100);
                }
            }
        }
    }

    private static void viewBooks() {
        UI.BookListUI();
        if (books.isEmpty()) {
            System.out.println("| No books found. Create one to get started!");
        }
        else {
            long startTime = System.currentTimeMillis();
            // Sort books here
            long endTime = System.currentTimeMillis();

            for (Book book : books) { System.out.println(book); }
            System.out.println("Total time taken: " + (endTime - startTime) + "ms");
        }
        System.out.println("======================================================================");
        Utility.pause(200);
    }

    private static void bookCreatorMenu() {
        String title, author, publisher, genre;
        int year, bookLength;
        boolean isAvailable;
        long ISBN = Long.parseLong(Utility.generateISBN13());

        System.out.println("\n=== CREATE BOOK ===");
        viewBooks();

        while (true) {
            System.out.print("Enter title: ");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("Enter author: ");
            author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("Author cannot be empty. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("Enter publisher: ");
            publisher = scanner.nextLine().trim();
            if (publisher.isEmpty() || publisher.isBlank()) {
                publisher = "Unknown Publisher";
            }

            System.out.print("What year did the book release: ");
            year = scanner.nextInt();
            scanner.nextLine();
            if (year <= 1200 || year > LocalDate.now().getYear() + 1) {
                System.out.println("Invalid year. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("What is the genre: ");
            genre = scanner.nextLine().trim();
            if (genre.isEmpty()) {
                System.out.println("Genre cannot be empty. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("Book Page Count: ");
            bookLength = scanner.nextInt();
            scanner.nextLine();
            if (bookLength <= 0) {
                System.out.println("Invalid book length. Try again.");
                Utility.pause(150);
                continue;
            }

            System.out.print("Is the book available? (Y/N): ");
            String temp = scanner.nextLine().trim();
            if (temp.equalsIgnoreCase("Y")) {
                isAvailable = true;
            } else if (temp.equalsIgnoreCase("N")) {
                isAvailable = false;
            } else {
                System.out.println("Invalid input, pick Y or N.");
                Utility.pause(150);
                continue;
            }
            break;
        }

        Book newBook = new Book(title, author, ISBN, publisher, year, genre, bookLength, isAvailable);
        books.add(newBook);
        csvHandler.appendRow(newBook);
        System.out.println("Book successfully added!");
        Utility.pause(200);
    }


    private static void bookDeletionMenu() {
        System.out.println("\n=== DELETE BOOK ===");
        viewBooks();

        if (books.isEmpty()) {
            System.out.println("No books to delete");
            Utility.pause(200);
            return;
        }

        System.out.print("Enter the ISBN of the book (NO HYPHENS): ");
        long input = scanner.nextLong();
        scanner.nextLine(); // consume leftover newline

        long IDtoDelete;
        try {
            IDtoDelete = Long.parseLong(String.valueOf(input));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number for ISBN");
            Utility.pause(200);
            return;
        }

        Book booktoRemove = books.stream()
                .filter(u -> u.getISBN() == IDtoDelete)
                .findFirst()
                .orElse(null);

        if (booktoRemove == null) {
            System.out.println("Book not found with ISBN: " + IDtoDelete);
            Utility.pause(200);
            return;
        }

        System.out.print("Are you sure you want to delete this book? (Y/N): ");
        String confirm = scanner.nextLine().trim();
        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        System.out.println("Removing: " + booktoRemove.getTitle());
        books.remove(booktoRemove);
        csvHandler.saveAll(books); // overwrite file
        System.out.println("Book successfully deleted!");
        Utility.pause(200);
    }


    private static void bookUpdateMenu() {

        System.out.println("\n=== UPDATE BOOK ===");
        viewBooks();

        if (books.isEmpty()) {
            System.out.println("No books to update.");
            Utility.pause(200);
            return;
        }

        System.out.print("Enter the ISBN of the book to update: ");
        String input = scanner.nextLine().trim();

        long ISBNToUpdate;
        try { ISBNToUpdate = Long.parseLong(input); }
        catch (NumberFormatException e) {
            System.out.println("Invalid ISBN format. Must be a number.");
            Utility.pause(200);
            return;
        }

        Book bookToUpdate = books.stream()
                .filter(u -> u.getISBN() == ISBNToUpdate)
                .findFirst()
                .orElse(null);

        if (bookToUpdate == null) {
            System.out.println("No book found with that ISBN.");
            Utility.pause(200);
            return;
        }

        // title, author, ISBN, publisher, year, genre, bookLength, isAvailable
        System.out.println("What needs updating?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. ISBN");
        System.out.println("4. Publisher");
        System.out.println("5. Year of Release");
        System.out.println("6. Genre");
        System.out.println("7. Book Length");
        System.out.println("8. Availability Status");
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
                System.out.print("Enter new title: ");
                bookToUpdate.setTitle(scanner.nextLine().trim());
            }
            case 2 -> {
                System.out.print("Enter new author (full name): ");
                bookToUpdate.setAuthor(scanner.nextLine().trim());
            }
            case 3 -> {
                System.out.print("Enter new ISBN: ");
                // VALIDATE ISBN HERE BEFORE SETTING IT
                bookToUpdate.setISBN(scanner.nextLong());
            }
            case 4 -> {
                System.out.print("Enter new publisher: ");
                bookToUpdate.setPublisher(scanner.nextLine());
            }
            default -> {
                System.out.println("Invalid option.");
                return;
            }
        }
    }
}
