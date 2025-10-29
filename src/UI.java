public class UI {

    public enum MainMenuOps {
        USER_CONFIGURATION(1, "USER CONFIGURATION"),
        LIBRARY_CONFIGURATION(2, "LIBRARY CONFIGURATION"),
        EXIT_APPLICATION(0, "EXIT APPLICATION");

        private final int code;
        private final String label;

        MainMenuOps(int code, String label) {
            this.code = code;
            this.label = label;
        }

        @Override public String toString() { return "|| " + code + ". " + label; }
    }

    public enum UserConfigOps {
        VIEW_USERS(1, "VIEW ALL USERS"),
        CREATE_USER(2, "CREATE NEW USER"),
        DELETE_USER(3, "DELETE USER"),
        UPDATE_USER(4, "UPDATE USER"),
        RETURN(0, "RETURN TO MAIN MENU");

        private final int code;
        private final String label;

        UserConfigOps(int code, String label) {
            this.code = code;
            this.label = label;
        }

        @Override public String toString() { return "|| " + code + ". " + label; }
    }

    public enum LibraryConfigOps {
        VIEW_BOOKS(1, "VIEW ALL BOOKS"),
        CREATE_BOOK(2, "CREATE A BOOK"),
        DELETE_BOOK(3, "DELETE A BOOK"),
        UPDATE_BOOK(4, "UPDATE A BOOK"),
        RETURN(0, "RETURN TO MAIN MENU");

        private final int code;
        private final String label;

        LibraryConfigOps(int code, String label) {
            this.code = code;
            this.label = label;
        }

        @Override public String toString() { return "|| " + code + ". " + label; }
    }


    public static void printMenu(String title, Enum<?>[] options) {
        System.out.println();
        System.out.println("===================================================");
        System.out.printf("%s%n", Utility.centerText(title, 51));
        System.out.println("===================================================");
        for (Enum<?> option : options) { System.out.println(option); }
        System.out.println("===================================================");
        System.out.print(">> ");
    }

    public static void UserListUI() {
        System.out.println();
        System.out.println("                              CURRENT USERS                            ");
        System.out.println("======================================================================");
        System.out.printf("| %-20s | %-10s | %-10s |%n", "Name", "Banner ID", "Role");
        System.out.println("----------------------------------------------------------------------");
    }

    public static void BookListUI() {
        System.out.println();
        System.out.println("                                                      CURRENT BOOKS                                                                       ");
        System.out.println("===========================================================================================================================================");
        System.out.printf("| %-25s | %-20s | %-17s | %-15s | %-6s | %-15s | %-5s | %-10s |%n",
                "Title", "Author", "ISBN", "Publisher", "Year", "Genre", "Pages", "Availability");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
    }

}
