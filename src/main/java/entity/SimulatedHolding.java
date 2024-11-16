package entity;

public interface SimulatedHolding {
    /**
     * Returns the stock symbol/ticker of the stock.
     * @return the stock symbol/ticker of the stock.
     */
    String getSymbol();

    /**
     * Returns the purchase price of the stock.
     * @return the purchase price of the stock.
     */
    double getPurchasePrice();

    /**
     * Returns the amount the stock that user purchased.
     * @return the amount the stock that user purchased.
     */
    int getPurchaseAmount();

}
