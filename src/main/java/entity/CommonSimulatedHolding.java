package entity;

/**
 * A simple implementation of the Simulated Holding interface.
 */
public class CommonSimulatedHolding implements SimulatedHolding {
    private String symbol;
    private double price;
    private int amount;

    public CommonSimulatedHolding(String symbol, double price, int amount) {
        this.symbol = symbol;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public double getPurchasePrice() {
        return price;
    }

    @Override
    public int getPurchaseAmount() {
        return amount;
    }
}
