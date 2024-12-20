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
    double getPurchaseAmount();

    /**
     * Sets the new purchase price of the stock.
     * @param purchasePrice the new purchase price of the stock.
     */
    void setPurchasePrice(double purchasePrice);

    /**
     * Sets the new purchase amount of the stock.
     * @param purchaseAmount the new purchase amount of the stock.
     */
    void setPurchaseAmount(double purchaseAmount);

    /**
     * Returns the amount of the stock that the user purchased.
     * @return the amount of the stock that the user purchased.
     */
    default double getAmount() {
        return getPurchaseAmount();
    }
}
