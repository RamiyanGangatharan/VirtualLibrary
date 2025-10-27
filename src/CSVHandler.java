import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    // NOTE: GPT MODDED THIS ONE
    private static final String DELIMITER = ",";
    private static final String CSV_PATH = "src/users.csv";

    // ===================== READ =====================

    /**
     * This method is used to read a CSV File
     * @return a list of users
     */
    public static List<LibraryUser> readCSV() {
        List<LibraryUser> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] values = line.split(DELIMITER);
                if (values.length < 4) continue;

                String firstName = values[0].trim();
                String lastName = values[1].trim();
                String role = values[2].trim();
                int bannerID = Integer.parseInt(values[3].trim());

                LibraryUser user = new LibraryUser(firstName, lastName, role);
                user.setBannerID(bannerID); // override auto-generated ID
                users.add(user);
            }

            System.out.println("CSV: " + CSV_PATH + " loaded successfully!");
        }
        catch (FileNotFoundException e) { System.err.println("CSV not found. A new one will be created when you save."); }
        catch (IOException e) { System.err.println("Error reading CSV: " + e.getMessage()); }
        return users;
    }

    // ===================== WRITE ALL =====================

    /**
     * This method will be used to write to a CSV File
     * @param users a user object created by the program
     */
    public static void writeAll(List<LibraryUser> users) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH))) {
            pw.println("firstName,lastName,role,bannerID");
            for (LibraryUser user : users) { pw.printf("%s,%s,%s,%d%n", user.getFirstName(), user.getLastName(), user.getRole(), user.getBannerID()); }
            System.out.println("CSV updated successfully!");
        } catch (IOException e) { System.err.println("Error writing CSV: " + e.getMessage()); }
    }

    // ===================== APPEND (add one row) =====================

    /**
     * This method is used to create a row of data pertaining to users
     * @param user a user object in the program.
     */
    public static void appendRow(LibraryUser user) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH, true))) {
            pw.printf("%s,%s,%s,%d%n", user.getFirstName(), user.getLastName(), user.getRole(), user.getBannerID());
        }
        catch (IOException e) { System.err.println("Error appending row: " + e.getMessage()); }
    }

    // ===================== FUTURE: UPDATE / DELETE =====================
    /**
     * This method is used to update a row of data pertaining to users
     * @param users user objects in the program.
     */
    public static void updateRow(List<LibraryUser> users) {
        writeAll(users);
    }

    /**
     * This method is used to delete a row of data pertaining to users
     * @param users user objects in the program.
     */
    public static void deleteRow(List<LibraryUser> users) {
        writeAll(users);
    }
}
