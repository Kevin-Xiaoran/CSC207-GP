package interface_adapter.home_view;

import entity.Stock;
import use_case.home_view.HomeInputBoundary;
import use_case.home_view.SearchInputData;
import use_case.home_view.WatchlistInputBoundary;

import java.util.ArrayList;

/**
 * The controller for the Login Use Case.
 */
public class WatchlistController {

    private final WatchlistInputBoundary watchlistInteractor;

    public WatchlistController(WatchlistInputBoundary watchlistInteractor) {
        this.watchlistInteractor = watchlistInteractor;
    }

    /**
     * Executes the Search Use Case.
     * @param symbol the username of the user logging in
     */
    public void search(String symbol) {
        final SearchInputData searchInputData = new SearchInputData(symbol);
        watchlistInteractor.search(searchInputData);
    }

    public void getWatchList() {
        watchlistInteractor.getWatchListData();
    }
}