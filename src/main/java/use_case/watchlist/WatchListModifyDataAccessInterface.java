package use_case.watchlist;

public interface WatchListModifyDataAccessInterface {
    /**
     * Add stock to watchlist.
     * @param symbol the stock's stock to add
     */
    void addToWatchList(String symbol);

    /**
     * Remove stock from watchlist.
     * @param symbol the stock's stock to add
     */
    void removeFromWatchList(String symbol);
}
