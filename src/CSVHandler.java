import java.io.*;
import java.util.ArrayList;
import java.util.List;

// note: GPT created this abstract class from a class I made prior
/**
 * A generic base class for handling CSV operations.
 * Subclasses should implement how to parse and serialize specific objects.
 *
 * @param <T> the type of object represented in the CSV file
 */
public abstract class CSVHandler<T> {
    protected static String csvPath = null;
    protected final String delimiter = ",";
    protected CSVHandler(String csvPath) { CSVHandler.csvPath = csvPath; }

    // ===================== ABSTRACT METHODS =====================

    /**
     * Converts a CSV line (split by delimiter) into an object of type T.
     */
    protected abstract T parseRow(String[] values);

    /**
     * Converts an object of type T into a CSV row string.
     */
    protected String toCSVRow(T obj) { return null; }

    // ===================== READ =====================

    public List<T> readCSV() {
        List<T> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] values = line.split(delimiter);
                T item = parseRow(values);
                if (item != null) items.add(item);
            }

            System.out.println("CSV: " + csvPath + " loaded successfully!");
        }
        catch (FileNotFoundException e) { System.err.println("CSV not found. A new one will be created when you save."); }
        catch (IOException e) { System.err.println("Error reading CSV: " + e.getMessage()); }
        return items;
    }

    // ===================== WRITE ALL =====================

    public void writeAll(List<T> items, String header) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvPath))) {
            pw.println(header);
            for (T item : items) pw.println(toCSVRow(item));
            System.out.println("CSV updated successfully!");
        }
        catch (IOException e) { System.err.println("Error writing CSV: " + e.getMessage()); }
    }

    // ===================== APPEND ONE =====================

    public void appendRow(T item) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvPath, true))) { pw.println(toCSVRow(item)); }
        catch (IOException e) { System.err.println("Error appending row: " + e.getMessage()); }
    }

    // ===================== UPDATE/DELETE =====================

    public void updateAll(List<T> items, String header) { writeAll(items, header); }
    public void deleteAll(List<T> items, String header) { writeAll(items, header); }
}
