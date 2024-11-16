package interface_adapter.portfolio;

import entity.SimulatedHolding;

import java.util.ArrayList;

/**
 * The state for the Signup View Model.
 */
public class PortfolioState {
    private ArrayList<SimulatedHolding> simulatedHoldings;

    public ArrayList<SimulatedHolding> getSimulatedHoldings() {
        return simulatedHoldings;
    }

    public void setSimulatedHoldings(ArrayList<SimulatedHolding> simulatedHoldings) {
        this.simulatedHoldings = simulatedHoldings;
    }
}
