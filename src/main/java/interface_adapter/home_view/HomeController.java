package interface_adapter.home_view;

import entity.Stock;
import use_case.home_view.HomeInputBoundary;
import use_case.home_view.SearchInputData;

import java.util.ArrayList;

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
        final SearchInputData searchInputData = new SearchInputData(symbol);
        homeUseCaseInteractor.search(searchInputData);
    }

    /**
     * Executes the "switch to Portfolio" Use Case.
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

    /**
     * Executes the "switch to SignupView" Use Case.
     */
    public void switchToSignupView() {
        homeUseCaseInteractor.switchToSignupView();
    }

    /**
     * Load watch list data.
     */
    public void getWatchList() {
        homeUseCaseInteractor.getWatchListData();
    }
}
