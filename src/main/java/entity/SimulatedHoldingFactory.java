package entity;

import java.util.ArrayList;

public interface SimulatedHoldingFactory {
    /**
     * Creates a new User.
     * @param symbol the symbol of stock
     * @param price the purchase price of stock
     * @param amount the amount that user purchased
     * @return the new simulated holding
     */
    SimulatedHolding create(String symbol, double price, int amount);
}
