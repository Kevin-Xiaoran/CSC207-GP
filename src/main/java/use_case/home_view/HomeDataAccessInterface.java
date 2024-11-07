package use_case.home_view;

import entity.Stock;

/**
 * DAO for the Login Use Case.
 */
public interface HomeDataAccessInterface {

    /**
     * Returns the stock with the given symbol.
     * @param symbol the stock symbol to look up
     * @return the stock with the given symbol
     */
    Stock getStock(String symbol);
}
