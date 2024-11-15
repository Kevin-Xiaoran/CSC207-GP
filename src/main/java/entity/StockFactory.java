package entity;

/**
 * Factory for creating stocks.
 */
public interface StockFactory {

    /**
     * Creates a new User.
     * @param symbol the symbol/ticker of the stock
     * @param openPrice the open price of the stock
     * @param closePrice the close price of the stock
     * @param volume the volume of the stock
     * @param high the highest price of the stock
     * @param low the lowest price of the stock
     * @return the new user
     */
    Stock create(String symbol, double openPrice, double closePrice, double volume, double high, double low);
}
