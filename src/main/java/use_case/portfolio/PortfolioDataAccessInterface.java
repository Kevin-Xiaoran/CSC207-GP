package use_case.portfolio;

import entity.SimulatedHolding;

import java.util.ArrayList;

public interface PortfolioDataAccessInterface {

    public ArrayList<SimulatedHolding> getPortfolioList();

    public void savePortfolioList();

    public void addToPortfolioList(SimulatedHolding simulatedHolding);

    public void removeFromPortfolioList(SimulatedHolding simulatedHolding);

}
