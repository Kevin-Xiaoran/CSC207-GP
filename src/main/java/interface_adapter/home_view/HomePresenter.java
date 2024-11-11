package interface_adapter.home_view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.home_view.HomeOutputBoundary;
import use_case.home_view.HomeOutputData;
import view.WatchListView;

/**
 * The Presenter for the Search Use Case.
 */
public class HomePresenter implements HomeOutputBoundary {

    private final HomeViewModel homeViewModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    private static final String WATCHLIST_VIEW_NAME = "WatchListView";

    public HomePresenter(HomeViewModel homeViewModel,
                         LoginViewModel loginViewModel,
                         SignupViewModel signupViewModel,
                         ViewManagerModel viewManagerModel) {
        this.homeViewModel = homeViewModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(HomeOutputData searchOutputData) {
        viewManagerModel.setState("StockView");
        viewManagerModel.firePropertyChanged();
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

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToStockView() {
        viewManagerModel.setState("StockView");
        viewManagerModel.firePropertyChanged();
    }
}
