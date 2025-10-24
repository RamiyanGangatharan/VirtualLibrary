import java.util.Objects;
import java.util.Random;

public class LibraryUser {
    private String firstName;
    private String lastName;
    private String role;
    private int bannerID;

    public enum BannerIDValues {
        ADMIN(900_000_000, 999_999_999),
        LIBRARIAN(800_000_000, 899_999_999),
        STUDENT(700_000_000, 799_999_999),
        MEMBER(600_000_000, 699_999_999);

        public final int min;
        public final int max;

        BannerIDValues(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }


    private static final Random random = new Random();

    // Getters & Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public int getBannerID() { return bannerID; }
    public void setBannerID(int bannerID) { this.bannerID = bannerID; }

    public LibraryUser(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;

        if (Objects.equals(role, "Admin")) {
            BannerIDValues value = BannerIDValues.ADMIN;
            this.bannerID = random.nextInt((value.max - value.min) + 1) + value.min;
        }

        if (Objects.equals(role, "Librarian")) {
            BannerIDValues value = BannerIDValues.LIBRARIAN;
            this.bannerID = random.nextInt((value.max - value.min) + 1) + value.min;
        }

        if (Objects.equals(role, "Student")) {
            BannerIDValues value = BannerIDValues.STUDENT;
            this.bannerID = random.nextInt((value.max - value.min) + 1) + value.min;
        }

        if (Objects.equals(role, "Member")) {
            BannerIDValues value = BannerIDValues.MEMBER;
            this.bannerID = random.nextInt((value.max - value.min) + 1) + value.min;
        }
    }

    // Default constructor
    public LibraryUser() { this("John", "Doe", "Member"); }

    @Override public String toString() { return String.format("| %-15s | %-10s | %-10s |", firstName + " " + lastName, bannerID, role); }
}
