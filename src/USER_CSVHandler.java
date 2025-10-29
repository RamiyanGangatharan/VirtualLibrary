import java.util.List;

public class USER_CSVHandler extends CSVHandler<LibraryUser> {

        public USER_CSVHandler() { super("src/users.csv"); }

        @Override protected LibraryUser parseRow(String[] values) {
            if (values.length < 4) return null;

            String firstName = values[0].trim();
            String lastName = values[1].trim();
            String role = values[2].trim();
            int bannerID = Integer.parseInt(values[3].trim());

            LibraryUser user = new LibraryUser(firstName, lastName, role);
            user.setBannerID(bannerID);
            return user;
        }

        @Override protected String toCSVRow(LibraryUser user) {
            return String.format("%s,%s,%s,%d",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole(),
                    user.getBannerID());
        }

        public void saveAll(List<LibraryUser> users) {
            writeAll(users, "firstName,lastName,role,bannerID");
        }
    }
