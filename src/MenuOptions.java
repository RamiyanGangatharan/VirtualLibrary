public class MenuOptions {
    /**
     * Enumerator for all menu options
     */
    enum MainMenuOptions {
        USER_CONFIGURATION("|| 1. USER CONFIGURATION"),
        EXIT_APPLICATION("|| 0. EXIT APPLICATION");

        private final String text;
        MainMenuOptions(String text) { this.text = text; }
        public String getText() { return text; }
    }
}
