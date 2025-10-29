import java.util.List;

public class BOOK_CSVHandler extends CSVHandler<Book> {

    protected BOOK_CSVHandler() { super("src/book.csv"); }

    /**
     * Converts a CSV line (split by delimiter) into an object of type T.
     *
     * @param values each item on the CSV
     */
    @Override protected Book parseRow(String[] values) {
        if (values.length < 4) return null;

        String title = values[0].trim();
        String author = values[1].trim();
        long ISBN = Long.parseLong(values[2].trim());
        String publisher = values[3].trim();
        int year = Integer.parseInt(values[4].trim());
        String genre = values[5].trim();
        int bookLength = Integer.parseInt(values[6].trim());
        boolean isAvailable = Boolean.parseBoolean(values[7].trim());

        return new Book(title, author, ISBN, publisher, year, genre, bookLength, isAvailable);
    }

    @Override protected String toCSVRow(Book book) {
        return String.format("%-25s, %-20s, %-15d, %-15s, %-6d, %-15s, %-5d, %-10s",
            book.getTitle(), book.getAuthor(), book.getISBN(), book.getPublisher(), book.getYear(),
            book.getGenre(), book.getBookLength(), book.isAvailable()
        );
    }

    public void saveAll(List<Book> books) {
        writeAll(books, "title, author, ISBN, publisher, year, genre, bookLength, isAvailable");
    }
}
