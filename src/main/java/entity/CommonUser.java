package entity;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final ArrayList<String> watchList;
    private final ArrayList<SimulatedHolding> portfolioList;

    public CommonUser(String name, String password, ArrayList<String> watchList, ArrayList<SimulatedHolding> portfolioList) {
        this.name = name;
        this.password = password;
        this.watchList = watchList;
        this.portfolioList = portfolioList;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public ArrayList<String> getWatchList() {
        return watchList;
    }

    @Override
    public ArrayList<SimulatedHolding> getPortfolioList() {
        return portfolioList;
    }
}
