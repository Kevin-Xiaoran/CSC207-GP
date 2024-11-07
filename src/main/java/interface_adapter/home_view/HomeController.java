package interface_adapter.home_view;

import use_case.home_view.HomeInputBoundary;
import use_case.home_view.HomeInputData;

/**
 * The controller for the Login Use Case.
 */
public class HomeController {

    private final HomeInputBoundary homeUseCaseInteractor;

    public HomeController(HomeInputBoundary homeUseCaseInteractor) {
        this.homeUseCaseInteractor = homeUseCaseInteractor;
    }

    /**
     * Executes the Search Use Case.
     * @param symbol the username of the user logging in
     */
    public void search(String symbol) {
        final HomeInputData searchInputData = new HomeInputData(symbol);
        homeUseCaseInteractor.search(searchInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToPortfolio() {
        homeUseCaseInteractor.switchToPortfolio();
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToWatchList() {
        homeUseCaseInteractor.switchToWatchList();
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        homeUseCaseInteractor.switchToLoginView();
    }
}
