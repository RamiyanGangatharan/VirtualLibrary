import java.util.Random;

public class LibraryUser {
    String firstName;
    String lastName;
    String role;
    int minValue = 100835223;
    int maxValue = 999999999;
    int bannerID = 0;

    Random random = new Random();
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getBannerID() { return bannerID; }
    public void setBannerID(int bannerID) { this.bannerID = bannerID; }

    public LibraryUser()
    {
        this.firstName = "John";
        this.lastName = "Doe";
        this.bannerID = random.nextInt((maxValue - minValue + 1) + minValue); // generates a random num regardless of user
        this.role = "MEMBER";
    }

    public LibraryUser(String firstName, String lastName, String role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bannerID = random.nextInt((maxValue - minValue + 1) + minValue); // generates a random num regardless of user;
        this.role = role;
    }

    @Override public String toString() {
        return String.format("| %-15s | %-10s | %-10s |", firstName + " " + lastName, bannerID, role);
    }
}
