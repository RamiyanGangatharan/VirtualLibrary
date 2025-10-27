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
