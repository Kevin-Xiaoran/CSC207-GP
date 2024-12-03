package use_case.watchlist;

import data_access.DBUserDataAccessObject;
import entity.Stock;
import use_case.home_view.SearchInputData;

import java.util.ArrayList;

/**
 * The  Interactor.
 */
public class WatchlistInteractor implements WatchlistInputBoundary {

    private final WatchListDataAccessInterface watchlistDataAccessInterface;
    private final WatchlistOutputBoundary watchlistPresenter;
    private final DBUserDataAccessObject dbUserDataAccessObject;

    public WatchlistInteractor(WatchListDataAccessInterface watchlistDataAccessInterface,
                               WatchlistOutputBoundary watchlistPresenter, DBUserDataAccessObject dbUserDataAccessObject) {
        this.watchlistDataAccessInterface = watchlistDataAccessInterface;
        this.watchlistPresenter = watchlistPresenter;
        this.dbUserDataAccessObject = dbUserDataAccessObject;
    }

    /**
     * Searches for a stock by its symbol.
     *
     * <p>This method retrieves stock data from the data access object using the provided stock symbol.
     * It validates the input and handles cases where the stock symbol is invalid or the stock data
     * cannot be found.</p>
     *
     * @param searchInputData the input data containing the stock symbol to search.
     *                        Must not be null or empty.
     * @throws IllegalArgumentException if the stock symbol in {@code searchInputData} is null, empty,
     *                                  or contains only whitespace.
     * @throws RuntimeException if the stock symbol does not correspond to any stock data,
     *                          or if an error occurs during the data retrieval process.
     */
    @SuppressWarnings({"checkstyle:IllegalCatch", "checkstyle:SuppressWarnings"})
    public void search(SearchInputData searchInputData) {
        final String stockSymbol = searchInputData.getStockSymbol();
        if (stockSymbol == null || stockSymbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be null or empty");
        }

        final Stock stock = dbUserDataAccessObject.getStock(stockSymbol);
        final SearchOutputData searchOutputData = new SearchOutputData(stock, false);
        watchlistPresenter.prepareSuccessView(searchOutputData);
    }

    @Override
    public Stock getStockData(String stockCode) {
        return dbUserDataAccessObject.getStock(stockCode);
    }

    @Override
    public void updateWatchlist(ArrayList<Stock> stocks) {
        System.out.println("Watchlist updated with " + stocks.size() + " stocks.");
    }
}