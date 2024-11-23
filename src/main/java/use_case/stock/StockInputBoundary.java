package use_case.stock;

import entity.Stock;

public interface StockInputBoundary {
    /**
     * Handles the logic when a user wants to buy a stock.
     * @param stock The stock to buy.
     */
    void buyStock(Stock stock);

    /**
     * Handles adding or removing a stock from the watchlist.
     * @param stock The stock to add/remove from the watchlist.
     */
    void toggleWatchlist(Stock stock);
}
