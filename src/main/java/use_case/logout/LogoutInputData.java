package use_case.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    private String userName;

    public LogoutInputData(String username) {
        // TODO: save the current username in an instance variable and add a getter.
        userName = username;
    }

    public String getUserName() {
        return userName;
    }

}
