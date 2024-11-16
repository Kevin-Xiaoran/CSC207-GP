package entity;

import java.util.ArrayList;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @param watchList the watch list of the new user
     * @param portfolioList the portfolio list of the new user
     * @return the new user
     */
    User create(String name, String password,
                ArrayList<String> watchList, ArrayList<SimulatedHolding> portfolioList);

}
