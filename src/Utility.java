import java.util.Random;

public class Utility {
    private static final Random randomBannerID = new Random();
    private static final Random randomISBN = new Random();

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
        return randomBannerID.nextInt((value.max - value.min) + 1) + value.min;
    }

    // Generates a valid ISBN-13
    public static String generateISBN13() {
        int[] digits = new int[12];

        // First three digits are usually "978" or "979"
        digits[0] = 9;
        digits[1] = 7;
        digits[2] = randomISBN.nextBoolean() ? 8 : 9;

        for (int i = 3; i < 12; i++) { digits[i] = randomISBN.nextInt(10); }

        int checkDigit = computeCheckDigit(digits);

        // Build ISBN string
        StringBuilder isbn = new StringBuilder();
        for (int d : digits) isbn.append(d);
        isbn.append(checkDigit);

        return isbn.toString();
    }

    // Computes check digit for a 12-digit ISBN prefix
    private static int computeCheckDigit(int[] digits) {
        int sum = 0;
        for (int i = 0; i < 12; i++) { sum += (i % 2 == 0) ? digits[i] : digits[i] * 3; }
        return (10 - (sum % 10)) % 10;
    }

    /**
     * Validates a long value as a possible ISBN-13.
     * @param isbnLong a 13-digit number representing the ISBN
     * @return the ISBN as a String if valid, otherwise null
     */
    public static String validateISBN13(long isbnLong) {
        String isbn = String.valueOf(isbnLong);

        // ISBN-13 must have exactly 13 digits
        if (isbn.length() != 13) return null;

        int[] digits = new int[13];
        for (int i = 0; i < 13; i++) { digits[i] = Character.getNumericValue(isbn.charAt(i)); }

        int checkDigit = computeCheckDigit(java.util.Arrays.copyOf(digits, 12));
        return (digits[12] == checkDigit) ? isbn : null;
    }

    public static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }
}
