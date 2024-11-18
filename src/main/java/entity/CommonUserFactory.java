package entity;

import java.util.ArrayList;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name,
                       String password,
                       ArrayList<String> watchList,
                       ArrayList<SimulatedHolding> portfolioList) {
        return new CommonUser(name, password, watchList, portfolioList);
    }
}
