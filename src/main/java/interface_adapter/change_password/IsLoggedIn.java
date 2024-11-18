package interface_adapter.change_password;

/**
 * Check whether the user is logged in.
 */
public class IsLoggedIn {
    private static boolean isLoggedIn;

    public IsLoggedIn() {
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }

    public static void setLoggedIn(boolean whetherLoggedIn) {
        IsLoggedIn.isLoggedIn = whetherLoggedIn;
    }
}
