/**
 * Represents a book in the library's catalog.
 * This class stores all essential information about a book,
 * including its bibliographic details, physical attributes,
 * and library-specific metadata used for managing availability and lending.
 *
 * <p>Each book has identifying information such as title, author, ISBN, and publisher,
 * along with details like genre, publication year, and the number of pages.
 * The class also tracks library-specific attributes such as unique book ID,
 * availability status, and borrower information.</p>
 *
 * <p>This class is a fundamental part of the library management system
 * and can be used to store, retrieve, and update book data.</p>
 *
 * <h2>Example Usage:</h2>
 * <pre>
 *     Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Scribner", 1925, "Fiction");
 *     book.setAvailable(true);
 */
public class Book {
    private String title;
    private String author;
    private long ISBN;
    private String publisher;
    private int year;
    private String genre;
    private int bookLength;
    private boolean isAvailable;


    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public long getISBN() { return ISBN; }
    public void setISBN(long ISBN) { this.ISBN = ISBN; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getBookLength() { return bookLength; }
    public void setBookLength(int bookLength) { this.bookLength = bookLength; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    /**
     * Default constructor
     */
    public Book() {
        this.title = "Pride and Prejudice";
        this.author = "Jane Austen";
        this.ISBN = 9780141439518L;
        this.publisher = "Penguin Classics";
        this.year = 1813;
        this.genre = "Classic Fiction";
        this.bookLength = 480;
        this.isAvailable = true;
    }

    /**
     * Parameterized constructor to create a book with custom attributes.
     *
     * @param title the title of the book
     * @param author the author of the book
     * @param ISBN the International Standard Book Number
     * @param publisher the publisher of the book
     * @param year the year the book was published
     * @param genre the genre or category of the book
     * @param bookLength the total number of pages in the book
     * @param isAvailable indicates whether the book is available for borrowing
     */
    public Book(String title, String author, long ISBN, String publisher, int year, String genre, int bookLength, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.year = year;
        this.genre = genre;
        this.bookLength = bookLength;
        this.isAvailable = isAvailable;
    }

    private String formatISBN(long isbn) {
        String s = String.format("%013d", isbn); // ensure 13 digits
        // Common ISBN-13 pattern: 978-0-14-143951-8
        return  s.substring(0, 3) + "-" +
                s.charAt(3) + "-" +
                s.substring(4, 6) + "-" +
                s.substring(6, 12) + "-" +
                s.substring(12);
    }

    @Override public String toString() {
        return String.format(
                "| %-25s | %-20s | %-17s | %-15s | %-6d | %-15s | %-5d | %-10s |",
                title, author, formatISBN(ISBN), publisher, year, genre, bookLength,
                (isAvailable ? "Available" : "Checked Out")
        );
    }
}
