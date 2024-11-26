package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonSimulatedHoldingFactory implements SimulatedHoldingFactory {
    @Override
    public SimulatedHolding create(String symbol, double price, double amount) {
        return new CommonSimulatedHolding(symbol, price, amount);
    }
}
