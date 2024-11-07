package use_case.home_view;

/**
 * The output boundary for the Home Use Case.
 */
public interface HomeOutputBoundary {

    /**
     * Prepares the success view for the Search Use Case.
     * @param searchOutputData the output data
     */
    void prepareSuccessView(HomeOutputData searchOutputData);

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the portfolio View.
     */
    void switchToPortfolio();

    /**
     * Switches to the watch list View.
     */
    void switchToWatchList();

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
