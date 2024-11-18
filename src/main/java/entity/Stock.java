package entity;

/**
 * The representation of a stock in our program.
 */
public interface Stock {

    /**
     * Returns the stock symbol/ticker of the stock.
     * @return the stock symbol/ticker of the stock.
     */
    String getSymbol();

    /**
     * Returns the stock open price of the stock.
     * @return the stock open price of the stock.
     */
    double getOpenPrice();

    /**
     * Returns the stock close price of the stock.
     * @return the stock close price of the stock.
     */
    double getClosePrice();

    /**
     * Returns the stock volume of the stock.
     * @return the stock volume of the stock.
     */
    double getVolume();

    /**
     * Returns the stock highest price of the stock.
     * @return the stock highest price of the stock.
     */
    double getHigh();

    /**
     * Returns the stock lowest price of the stock.
     * @return the stock lowest price of the stock.
     */
    double getLow();

    /**
     * Returns the stock dailyChange price of the stock.
     * @return the stock dailyChange price of the stock.
     */
    double getDailyChange();

    /**
     * Returns the stock dailyPercentage of  price of the stock.
     * @return the stock dailyPercentage of  price of the stock.
     */
    double getDailyPercentage();
}
