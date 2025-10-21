public class Utility {
    public static void pause(int ms) { try { Thread.sleep(ms); } catch (InterruptedException ignored) {}}

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
