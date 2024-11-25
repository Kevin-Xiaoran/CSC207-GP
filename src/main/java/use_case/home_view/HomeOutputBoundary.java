package use_case.home_view;

import entity.Stock;

import java.util.ArrayList;

/**
 * The output boundary for the Home Use Case.
 */
public interface HomeOutputBoundary {

    /**
     * Prepares the success view for the Search Use Case.
     * @param searchOutputData the output data
     */
    void prepareSuccessView(SearchOutputData searchOutputData);

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the watch list View.
     */
    void switchToPortfolio();

    /**
     * Switches to the watch list View.
     * @param watchListSymbols the symbols of whole watch list
     */
    void switchToWatchList(ArrayList<String> watchListSymbols);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();

    /**
     * Switches to the Signup View.
     */
    void switchToSignupView();

    /**
     * Preload watchlist data for Home View.
     * @param watchList watchList data
     */
    void getWatchListData(ArrayList<Stock> watchList);

    /**
     * Delete watchlist and portfolio data, and updates relative views
     */
    void deleteLocalData();
}
