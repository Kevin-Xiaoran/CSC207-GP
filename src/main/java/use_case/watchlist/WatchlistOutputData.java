package use_case.watchlist;

import entity.Stock;
import java.util.ArrayList;

/**
 * The output boundary for the Home Use Case.
 */
public interface WatchlistOutputData {

    /**
     * Prepares the success view for the Search Use Case.
     *
     * @param searchOutputData the output data
     */
    void prepareSuccessView(SearchOutputData searchOutputData);

    /**
     * Prepares the failure view for the Search Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}