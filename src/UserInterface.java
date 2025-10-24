public class UserInterface {

    public static void StartUI() {
        System.out.println("===================================================");
        System.out.println("         Welcome to our Virtual Library            ");
        System.out.println("===================================================");
        System.out.println(MenuOptions.MainMenuOptions.USER_CONFIGURATION.getText());
        System.out.println(MenuOptions.MainMenuOptions.EXIT_APPLICATION.getText());
        System.out.println("===================================================");
        System.out.print(">> ");
    }

    public static void UserConfigUI() {
        System.out.println("                       USER CONFIGURATION MENU                       ");
        System.out.println("=====================================================================");
        System.out.println("|| 1. VIEW ALL USERS");
        System.out.println("|| 2. CREATE NEW USER");
        System.out.println("|| 3. DELETE USER");
        System.out.println("|| 4. UPDATE USER");
        System.out.println("|| 0. RETURN TO MAIN MENU");
        System.out.println("=====================================================================");
        System.out.print(">> ");
    }

    public static void UserListUI() {
        System.out.println("                              CURRENT USERS                            ");
        System.out.println("======================================================================");
        System.out.printf("| %-20s | %-10s | %-10s |%n", "Name", "Banner ID", "Role");
        System.out.println("----------------------------------------------------------------------");
    }
}
