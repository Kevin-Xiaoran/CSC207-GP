package entity;

import java.util.ArrayList;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the watch list of the user.
     * @return the watch list of the user.
     */
    ArrayList<String> getWatchList();

    /**
     * Returns the portfolio of the user.
     * @return the portfolio of the user.
     */
    ArrayList<Stock> getPortfolioList();

}
