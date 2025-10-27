import java.util.Random;

public class Utility {
    private static final Random random = new Random();

    public static void pause(int ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {}}

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) { return input; }
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static int generateBannerID(String role) {
        enum BannerIDValues {
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

        BannerIDValues value = switch (role) {
            case "Admin" -> BannerIDValues.ADMIN;
            case "Librarian" -> BannerIDValues.LIBRARIAN;
            case "Student" -> BannerIDValues.STUDENT;
            case "Member" -> BannerIDValues.MEMBER;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
        return random.nextInt((value.max - value.min) + 1) + value.min;
    }

    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
}
