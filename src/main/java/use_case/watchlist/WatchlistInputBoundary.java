package use_case.home_view;

import entity.Stock;

import java.util.ArrayList;

public interface WatchlistInputBoundary {

    /**
     * Executes the search use case.
     * @param searchInputData the input data
     */
    void search(SearchInputData searchInputData);


    /**
     * Executes the search use case.
     * @param stocks the input data
     */

    void updateWatchlist(ArrayList<Stock> stocks);


    /**
     * Executes the search use case.
     * @param stockCode the input data
     */

    Stock getStockData(String stockCode);
}
