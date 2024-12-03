package use_case.watchlist;

import entity.Stock;

import java.util.ArrayList;

/**
 * The output boundary for the Home Use Case.
 */
public interface WatchlistOutputBoundary {

    /**
     * Prepares the success view for the Search Use Case.
     *
     * @param searchOutputData the output data
     */
    void prepareSuccessView(SearchOutputData searchOutputData);

}