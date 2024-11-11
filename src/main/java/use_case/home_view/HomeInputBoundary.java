package use_case.home_view;

public interface HomeInputBoundary {

    /**
     * Executes the search use case.
     * @param searchInputData the input data
     */
    void search(HomeInputData searchInputData);

    /**
     * Executes the switch to portfolio view use case.
     */
    void switchToPortfolio();

    /**
     * Executes the switch to watch list view use case.
     */
    void switchToWatchList();

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to Signup view use case.
     */
    void switchToSignupView();

}
