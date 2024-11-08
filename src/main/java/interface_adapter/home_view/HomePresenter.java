package interface_adapter.home_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.home_view.HomeOutputBoundary;
import use_case.home_view.HomeOutputData;
import view.WatchListView;

/**
 * The Presenter for the Search Use Case.
 */
public class HomePresenter implements HomeOutputBoundary {

    private final HomeViewModel homeViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    private static final String WATCHLIST_VIEW_NAME = "WatchListView";

    public HomePresenter(HomeViewModel homeViewModel,
                         LoginViewModel loginViewModel,
                         ViewManagerModel viewManagerModel) {
        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(HomeOutputData searchOutputData) {
        // Present stock view
        System.out.println("Show stock view after searching stock");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Show error dialog
        System.out.println("Show dialog view after failed to search stock");
    }

    @Override
    public void switchToPortfolio() {
        System.out.println("Show portfolio view");
    }

    @Override
    public void switchToWatchList() {
        viewManagerModel.setState("WatchListView");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
