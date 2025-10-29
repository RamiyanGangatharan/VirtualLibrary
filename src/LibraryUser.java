/**
 * Represents a user of the library system.
 * <p>
 * The {@code LibraryUser} class stores key details about a library user,
 * including their personal information (first name, last name), assigned role,
 * and unique banner ID. This class serves as a foundation for managing user
 * accounts, identifying roles (such as Admin, Librarian, or Member), and
 * generating unique IDs for each user through the {@link Utility#generateBannerID(String)} method.
 * </p>
 *
 * <p>
 * The class provides getter and setter methods for encapsulated access,
 * as well as a formatted {@code toString()} method for clean display in user tables.
 * </p>
 *
 * <h2>Example Usage:</h2>
 * <pre>
 *     LibraryUser user = new LibraryUser("Alice", "Johnson", "Librarian");
 *     System.out.println(user);
 * </pre>
 */
public class LibraryUser {
    private String firstName;
    private String lastName;
    private String role;
    private int bannerID;

    // Getters & Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getBannerID() { return bannerID; }
    public void setBannerID(int bannerID) { this.bannerID = bannerID; }

    /**
     * This is a constructor to define the structure for a user.
     * @param firstName firstname of the user
     * @param lastName lastname of the user
     * @param role role of the user
     */
    public LibraryUser(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.bannerID = Utility.generateBannerID(role);
    }

    // Default constructor
    public LibraryUser() { this("John", "Doe", "Member"); }

    /**
     * Makes the output of the user table clean
     * @return a formatted string for the display of users
     */
    @Override public String toString() { return String.format("| %-15s | %-10s | %-10s |", firstName + " " + lastName, bannerID, role); }
}
