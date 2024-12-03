package use_case.stock;

import entity.Stock;

/**
 * Input Boundary for actions which are related to Stock.
 */
public interface StockInputBoundary {
    /**
     * Handles the logic when a user wants to buy a stock.
     * @param stock The stock to buy.
     */
    void buyStock(Stock stock);

    /**
     * Handles adding or removing a stock from the watchlist.
     * @param stock The stock to add/remove from the watchlist.
     * @param shouldModifyData To decide if we should modify data or not.
     */
    void toggleWatchlist(Stock stock, Boolean shouldModifyData);
}
