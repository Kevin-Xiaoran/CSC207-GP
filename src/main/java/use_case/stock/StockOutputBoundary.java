package use_case.stock;

import entity.Stock;

/**
 * Output Boundary for actions which are related to stock.
 */
public interface StockOutputBoundary {
    /**
     * Presents the Buy View when a user wants to buy a stock.
     * @param stock The stock to buy.
     */
    void presentBuyView(Stock stock);

    /**
     * Presents the updated watchlist after adding a stock.
     * @param stock The stock that was added.
     */
    void presentAddToWatchlist(Stock stock);

    /**
     * Presents the updated watchlist after removing a stock.
     * @param stock The stock that was removed.
     */
    void presentRemoveFromWatchlist(Stock stock);

    /**
     * Updates favourite button UI.
     * @param isFavourite is stock in watchlist.
     */
    void updateFavouriteButton(Boolean isFavourite);
}
