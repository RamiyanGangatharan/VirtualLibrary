import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    // NOTE: GPT MODDED THIS ONE
    private static final String DELIMITER = ",";
    private static final String CSV_PATH = "src/users.csv";

    // ===================== READ =====================
    public static List<LibraryUser> readCSV() {
        List<LibraryUser> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_PATH))) {
            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {
                if (skipHeader) { // skip the header
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

            System.out.println("CSV loaded successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("CSV not found. A new one will be created when you save.");
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }

        return users;
    }

    // ===================== WRITE ALL =====================
    public static void writeAll(List<LibraryUser> users) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH))) {
            pw.println("firstName,lastName,role,bannerID");
            for (LibraryUser user : users) {
                pw.printf("%s,%s,%s,%d%n",
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole(),
                        user.getBannerID());
            }
            System.out.println("CSV updated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }

    // ===================== APPEND (add one row) =====================
    public static void appendRow(LibraryUser user) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_PATH, true))) {
            pw.printf("%s,%s,%s,%d%n",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole(),
                    user.getBannerID());
        } catch (IOException e) {
            System.err.println("Error appending row: " + e.getMessage());
        }
    }

    // ===================== FUTURE: UPDATE / DELETE =====================
    // These are simplified: you just call writeAll() after editing the list.
    public static void updateRow(List<LibraryUser> users) {
        writeAll(users);
    }

    public static void deleteRow(List<LibraryUser> users) {
        writeAll(users);
    }
}
