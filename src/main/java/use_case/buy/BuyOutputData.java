package use_case.buy;

import entity.SimulatedHolding;

/**
 * Output Data for the Buy Use Case.
 */
public class BuyOutputData {
    private final SimulatedHolding simulatedHolding;

    public BuyOutputData(SimulatedHolding simulatedHolding) {
        this.simulatedHolding = simulatedHolding;
    }

    public SimulatedHolding getSimulatedHolding() {
        return simulatedHolding;
    }
}
