public class BannerID {
    /**
     * Enumerator to store banner ID parameters
     */
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
}
