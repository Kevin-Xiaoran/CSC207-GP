package interface_adapter.portfolio;

import entity.SimulatedHolding;
import entity.Stock;

import java.util.ArrayList;

/**
 * The state for the Signup View Model.
 */
public class PortfolioState {
    private ArrayList<Stock> stocks;
    private ArrayList<SimulatedHolding> simulatedHoldings;

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public ArrayList<SimulatedHolding> getSimulatedHoldings() {
        return simulatedHoldings;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setSimulatedHoldings(ArrayList<SimulatedHolding> simulatedHoldings) {
        this.simulatedHoldings = simulatedHoldings;
    }

    public void addToPortfolioList(SimulatedHolding simulatedHolding) {
        simulatedHoldings.add(simulatedHolding);
    }
}
